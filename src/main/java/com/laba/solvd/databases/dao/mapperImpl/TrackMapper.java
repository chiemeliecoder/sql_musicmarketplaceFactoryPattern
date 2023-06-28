package com.laba.solvd.databases.dao.mapperImpl;

import com.laba.solvd.databases.interfacedao.ITrackDAO;
import com.laba.solvd.databases.interfacedao.IUserDAO;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.Track;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.myBatis.MyBatisObject;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class TrackMapper implements ITrackDAO {

  private SqlSession sqlSession;

  public TrackMapper() {
    this.sqlSession = sqlSession;
  }

  @Override
  public void create(Track entity, Integer id) {
    entity.setId(id);
    sqlSession.insert("create", entity);
  }

  @Override
  public void createNoID(Track entity) {
    sqlSession.insert("create", entity);
  }

  @Override
  public List<Track> findAll() {
    try{
      return sqlSession.selectList("findAll");
    }finally {
      sqlSession.close();
    }
  }

  @Override
  public Track getById(int id) throws SQLException {
    return sqlSession.selectOne("getById", id);
  }


//  @Override
//  public void setPurchase(Playlist playlists, User user) {
//
//    try (SqlSession session = MyBatisObject.getSqlSession()) {
//      IUserDAO userDao = session.getMapper(IUserDAO.class);
//      userDao.setPlaylistsList(playlists, user);
//      session.commit();
//    }
//  }

  /**
   * Retrieve an object that was previously persisted to the database using
   *
   * @param id
   */
  @Override
  public Track read(int id) {
    return null;
  }

  @Override
  public void update(Track entity) {

  }

  @Override
  public void delete(int id) {

  }

  @Override
  public List<Track> getAll() {
    return null;
  }


}
