package com.laba.solvd.databases.service;

import com.laba.solvd.databases.dao.AlbumDAO;
import com.laba.solvd.databases.interfacedao.IAlbumDAO;
import com.laba.solvd.databases.model.Album;
import com.laba.solvd.databases.model.Track;
import com.laba.solvd.databases.service.interfaceservice.IAlbumService;
import com.laba.solvd.databases.service.interfaceservice.ITrackService;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumServiceImpl implements IAlbumService {

  private IAlbumDAO albumDAO;
  private ITrackService trackService;

  public AlbumServiceImpl() {
    this.albumDAO = new AlbumDAO();
    this.trackService = new TrackImpl();
  }

  @Override
  public Album create(Album entity, Integer id) {
    if (entity == null) {
      throw new IllegalArgumentException("Album entity cannot be null");
    }
    entity.setId(null);
    albumDAO.create(entity,id);

    if(entity.getTracks() != null){
      List<Track> tracks = entity.getTracks().stream()
          .map(track -> trackService.create(track, entity.getId())).collect(
              Collectors.toList());
      entity.setTracks(tracks);
    }
    return entity;
  }

  @Override
  public List<Album> getAll() {
    return albumDAO.getAll();
  }
}
