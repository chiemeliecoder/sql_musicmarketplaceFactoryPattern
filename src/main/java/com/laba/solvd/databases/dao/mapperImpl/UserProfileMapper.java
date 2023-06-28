package com.laba.solvd.databases.dao.mapperImpl;

import com.laba.solvd.databases.interfacedao.IUserProfileDAO;
import com.laba.solvd.databases.model.UserProfile;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class UserProfileMapper implements IUserProfileDAO {
  private  SqlSession sqlSession;

  public UserProfileMapper() {
    this.sqlSession = sqlSession;
  }

  @Override
  public UserProfile getUserProfileById(int userId) throws SQLException {
    return sqlSession.selectOne("com.laba.solvd.databases.interfacedao.IUserProfileDAO.getUserProfileById", userId);
  }

  @Override
  public void createUser(UserProfile user) {
    sqlSession.insert("createUser", user);
  }

  @Override
  public List<UserProfile> getAllUsersProfiles() {
    return sqlSession.selectList("com.laba.solvd.databases.interfacedao.IUserProfileDAO.getAllUsersProfiles");
  }

  @Override
  public List<UserProfile> getUsersWithBioContaining(String keyword) {
    return sqlSession.selectList("com.laba.solvd.databases.interfacedao.IUserProfileDAO.getUsersWithBioContaining", keyword);
  }
}
