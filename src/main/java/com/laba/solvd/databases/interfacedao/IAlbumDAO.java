package com.laba.solvd.databases.interfacedao;

import com.laba.solvd.databases.model.Album;
import java.sql.SQLException;
import java.util.List;

public interface IAlbumDAO {

  Album getById(int id) throws SQLException;
  void create(Album entity, Integer id);

  /** Retrieve an object that was previously persisted to the database using
   */
  Album read(int id);
  void update(Album entity);
  void delete(int id);
  List<Album> getAll();

}
