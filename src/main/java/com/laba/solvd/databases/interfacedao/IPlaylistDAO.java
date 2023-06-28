package com.laba.solvd.databases.interfacedao;

import com.laba.solvd.databases.model.Playlist;
import java.sql.SQLException;
import java.util.List;

public interface IPlaylistDAO {

  Playlist getById(int id) throws SQLException;
  void create(Playlist entity, Integer id);

  /** Retrieve an object that was previously persisted to the database using
   */
  Playlist read(int id);
  void update(Playlist entity);
  void delete(int id);
  List<Playlist> getAll();

}
