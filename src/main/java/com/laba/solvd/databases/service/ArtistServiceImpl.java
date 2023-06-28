package com.laba.solvd.databases.service;

import com.laba.solvd.databases.dao.ArtistDAO;
import com.laba.solvd.databases.interfacedao.IArtistDAO;
import com.laba.solvd.databases.model.Album;
import com.laba.solvd.databases.model.Artists;
import com.laba.solvd.databases.service.interfaceservice.IAlbumService;
import com.laba.solvd.databases.service.interfaceservice.IArtistService;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistServiceImpl implements IArtistService {

 private final IArtistDAO genericDAO;
 private final IAlbumService albumService;

  public ArtistServiceImpl() {
    this.genericDAO = new ArtistDAO();
    this.albumService = new AlbumServiceImpl();
  }

  @Override
  public Artists createArt(Artists entity, Integer id) {
    if (entity == null) {
      throw new IllegalArgumentException("Artist entity cannot be null");
    }

    entity.setId(null);
    genericDAO.create(entity, id);

    if(entity.getAlbums() != null){
      List<Album> albums = entity.getAlbums().stream()
          .map(album -> albumService.create(album, entity.getId())).collect(
              Collectors.toList());
      entity.setAlbums(albums);
    }
    return entity;
  }




  @Override
  public List<Artists> getAllArtists() {
    return genericDAO.getAll();
  }
}
