package com.laba.solvd.databases.interfacedao;

import com.laba.solvd.databases.model.ArtistAchievement;
import com.laba.solvd.databases.model.Artists;
import java.sql.SQLException;
import java.util.List;

public interface IArtistDAO {
  Artists getById(int id) throws SQLException;
  void create(Artists entity, Integer id);

  /** Retrieve an object that was previously persisted to the database using
   */
  Artists read(int id);
  void update(Artists entity);
  void delete(int id);
  List<Artists> getAll();

}
