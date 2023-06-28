package com.laba.solvd.databases.dao.mapperImpl;

import com.laba.solvd.databases.interfacedao.IWishlistDAO;
import com.laba.solvd.databases.model.Wishlist;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class WishlistMapper implements IWishlistDAO {

  private SqlSession sqlSession;

  public WishlistMapper() {
    this.sqlSession = sqlSession;
  }

  @Override
  public void create(Wishlist entity) {
    sqlSession.insert("create",entity);
  }

  @Override
  public List<Wishlist> getAll() {
    try{
      return sqlSession.selectList("getAll");
    }finally {
      sqlSession.close();
    }
  }

  /**
   * Retrieve an object that was previously persisted to the database using
   *
   * @param id
   */
  @Override
  public Wishlist read(int id) {
    return null;
  }

  @Override
  public void update(Wishlist entity) {

  }

  @Override
  public void delete(int id) {

  }

  @Override
  public Wishlist getById(int id) throws SQLException {
    return null;
  }


}
