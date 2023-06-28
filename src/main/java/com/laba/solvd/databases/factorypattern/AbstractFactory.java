package com.laba.solvd.databases.factorypattern;

import com.laba.solvd.databases.interfacedao.IAlbumDAO;
import com.laba.solvd.databases.interfacedao.IArtistDAO;
import com.laba.solvd.databases.interfacedao.IPlaylistDAO;
import com.laba.solvd.databases.interfacedao.ITrackDAO;
import com.laba.solvd.databases.interfacedao.IUserDAO;
import com.laba.solvd.databases.interfacedao.IUserProfileDAO;
import com.laba.solvd.databases.interfacedao.IWishlistDAO;

public abstract class AbstractFactory {

  public abstract IUserDAO getUserDAO();
  public abstract IUserProfileDAO getUserProfileDAO();
  public abstract IPlaylistDAO getPlaylistDAO();
  public abstract IArtistDAO getArtistDAO();
  public abstract IAlbumDAO getAlbumDAO();
  public abstract ITrackDAO getTrackDAO();
  public abstract IWishlistDAO getWishlistDAO();


  // Other common methods can be defined here
  // public abstract IConnection getConnection();

}
