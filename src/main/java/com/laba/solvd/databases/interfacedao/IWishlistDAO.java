package com.laba.solvd.databases.interfacedao;

import com.laba.solvd.databases.model.Wishlist;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IWishlistDAO {

  Wishlist getById(int id) throws SQLException;
  void create(Wishlist entity);

  /** Retrieve an object that was previously persisted to the database using
   */
  Wishlist read(int id);
  void update(Wishlist entity);
  void delete(int id);
  List<Wishlist> getAll();

}
