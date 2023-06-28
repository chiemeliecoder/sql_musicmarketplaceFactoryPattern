package com.laba.solvd.databases.factorypattern;

import com.laba.solvd.databases.dao.AlbumDAO;
import com.laba.solvd.databases.dao.ArtistDAO;
import com.laba.solvd.databases.dao.PlaylistDAO;
import com.laba.solvd.databases.dao.TrackDAO;
import com.laba.solvd.databases.dao.UserDAO;
import com.laba.solvd.databases.dao.UserProfileDAO;
import com.laba.solvd.databases.dao.WishlistDAO;
import com.laba.solvd.databases.interfacedao.IAlbumDAO;
import com.laba.solvd.databases.interfacedao.IArtistDAO;
import com.laba.solvd.databases.interfacedao.IPlaylistDAO;
import com.laba.solvd.databases.interfacedao.ITrackDAO;
import com.laba.solvd.databases.interfacedao.IUserDAO;
import com.laba.solvd.databases.interfacedao.IUserProfileDAO;
import com.laba.solvd.databases.interfacedao.IWishlistDAO;

public class DatabaseDAOFactory extends AbstractFactory{

  // Implement factory methods for other DAO interfaces
  @Override
  public IUserDAO getUserDAO() {
    return new UserDAO(); // Return an instance of the UserDAO implementation for the database
  }

  @Override
  public IUserProfileDAO getUserProfileDAO() {
    return new UserProfileDAO();
  }

  @Override
  public IPlaylistDAO getPlaylistDAO() {
    return new PlaylistDAO();
  }

  @Override
  public IArtistDAO getArtistDAO() {
    return new ArtistDAO();
  }

  @Override
  public IAlbumDAO getAlbumDAO() {
    return new AlbumDAO();
  }

  @Override
  public ITrackDAO getTrackDAO() {
    return new TrackDAO();
  }

  @Override
  public IWishlistDAO getWishlistDAO() {
    return new WishlistDAO();
  }


  // Implement other common methods if required
  // public IConnection getConnection() {
  //     return new DatabaseConnection(); // Return a connection object for the database
  // }
}
