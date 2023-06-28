package com.laba.solvd.databases.service;

import com.laba.solvd.databases.dao.TrackDAO;
import com.laba.solvd.databases.interfacedao.ITrackDAO;
import com.laba.solvd.databases.model.Track;
import com.laba.solvd.databases.service.interfaceservice.ITrackService;
import java.util.List;

public class TrackImpl implements ITrackService {
  private final ITrackDAO trackDAO;

  public TrackImpl() {
    this.trackDAO = new TrackDAO();
  }


  @Override
  public Track create(Track entity, Integer id) {
    trackDAO.create(entity,id);
    return entity;
  }

  @Override
  public List<Track> getAll() {
    return trackDAO.getAll();
  }
}
