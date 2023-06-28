package com.laba.solvd.databases.dao.mapperImpl;

import com.laba.solvd.databases.interfacedao.IPlaylistDAO;
import com.laba.solvd.databases.interfacedao.IUserDAO;
import com.laba.solvd.databases.interfacedao.IUserProfileDAO;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import com.laba.solvd.databases.myBatis.MyBatisObject;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class UserMapper implements IUserDAO {
  private SqlSession sqlSession;

  public UserMapper() {
    this.sqlSession = sqlSession;
  }

  @Override
  public void createUser(User user) {
    sqlSession.insert("createUser",user);
  }


  @Override
  public List<User> findAll() {
    try{
      return sqlSession.selectList("findAll");
    }finally {
      sqlSession.close();
    }

  }

  @Override
  public void setPlaylistsList(Playlist playlists, User user) {

    try (SqlSession session = MyBatisObject.getSqlSession()) {
      IUserDAO userDao = session.getMapper(IUserDAO.class);
      userDao.setPlaylistsList(playlists, user);
      session.commit();
    }
  }

  @Override
  public void setUserProfile(UserProfile userProfile, User user) {
    try (SqlSession session = MyBatisObject.getSqlSession()) {
      IUserDAO userDao = session.getMapper(IUserDAO.class);
      userDao.setUserProfile(userProfile, user);
      session.commit();
    }

  }

  @Override
  public User getUserById(int userId) throws SQLException {
    return null;
  }



  @Override
  public List<User> getAllUsers() {
    return null;
  }



  @Override
  public UserProfile retrieveUserProfileById(int userProfileId) {
    return null;
  }

//  @Override
//  public User getUserByIdSQL(int userId) {
//    return null;
//  }




}
