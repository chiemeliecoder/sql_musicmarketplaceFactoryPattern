package com.laba.solvd.databases.interfacedao;


import com.laba.solvd.databases.model.UserProfile;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserProfileDAO {

  UserProfile getUserProfileById(int userId) throws SQLException;
  void createUser(UserProfile user);
  List<UserProfile> getAllUsersProfiles();
  List<UserProfile> getUsersWithBioContaining(String keyword);

}
