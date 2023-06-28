package com.laba.solvd.databases.factorypattern;

import com.laba.solvd.databases.dao.UserDAO;
import com.laba.solvd.databases.dao.UserProfileDAO;
import com.laba.solvd.databases.interfacedao.IAlbumDAO;
import com.laba.solvd.databases.interfacedao.IArtistDAO;
import com.laba.solvd.databases.interfacedao.IPlaylistDAO;
import com.laba.solvd.databases.interfacedao.ITrackDAO;
import com.laba.solvd.databases.interfacedao.IUserDAO;
import com.laba.solvd.databases.interfacedao.IUserProfileDAO;
import com.laba.solvd.databases.interfacedao.IWishlistDAO;
import com.laba.solvd.databases.model.Artists;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.Purchase;
import com.laba.solvd.databases.model.Track;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import com.laba.solvd.databases.model.Wishlist;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.javassist.NotFoundException;

public class FactoryMain {

  public static void main(String args[]) throws SQLException, NotFoundException {
    AbstractFactory factory = new DatabaseDAOFactory();

    IUserDAO userDAO = factory.getUserDAO();
    IUserProfileDAO userProfileDAO = factory.getUserProfileDAO();
    IArtistDAO artistDAO = factory.getArtistDAO();
    IAlbumDAO albumDAO = factory.getAlbumDAO();
    IPlaylistDAO playlistDAO = factory.getPlaylistDAO();
    ITrackDAO trackDAO = factory.getTrackDAO();
    IWishlistDAO wishlistDAO = factory.getWishlistDAO();


    Calendar cal = Calendar.getInstance();
    cal.set(2023, Calendar.MARCH, 28);
    Date date = cal.getTime();
    java.sql.Date d = new java.sql.Date(date.getTime());

    Time time = Time.valueOf(LocalTime.of(03,01,00));

    BigDecimal p = BigDecimal.valueOf(9.99);
    BigDecimal r = BigDecimal.valueOf(4.5);


    int userId = 1;
    // Retrieve a user by ID
    User user = userDAO.getUserById(userId);
    System.out.println("User: " + user);

    // Retrieve all playlists for a user
    List<Playlist> playlists = playlistDAO.getAll();
    System.out.println("Playlist: " + playlists);

    // Search for tracks by a specific keyword
    List<Track> tracks = trackDAO.findAll();
    System.out.println("Tracks: " + tracks);




    // Create a new user profile
    UserProfileDAO use = new UserProfileDAO();
    int usepNum = use.getMaxUserProfId();
    UserProfile userProfile = new UserProfile();
    userProfile.setId(usepNum + 1);
    userProfile.setBio("We like Beyonce");
    userProfile.setProfileimage("me.jpg");
    userProfile.setLocation("Germany");

    userProfileDAO.createUser(userProfile);
    System.out.println("New User Profile created!");


    // Update wishlist information

    Wishlist wish = wishlistDAO.getById(3);
    User users = userDAO.getUserById(1);
    wish.setUser(users);
    wish.setName("Ayase's Album");
    wishlistDAO.update(wish);
    System.out.println("Wishlist updated!");





  }

}
