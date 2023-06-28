package com.laba.solvd.databases.interfacedao;

import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.Purchase;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDAO {
  User getUserById(int userId) throws SQLException;
  void createUser(User user);
  List<User> getAllUsers();
  List<User> findAll();
  UserProfile retrieveUserProfileById(int userProfileId);
  //User getUserByIdSQL(int userId);
  void setPlaylistsList(Playlist playlists,User user);
  void setUserProfile(UserProfile userProfile,User user);

}
