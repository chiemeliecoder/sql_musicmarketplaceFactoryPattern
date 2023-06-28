package com.laba.solvd.databases.dao;

import com.laba.solvd.databases.configurations.ConnectionPool;
import com.laba.solvd.databases.interfacedao.ITrackDAO;
import com.laba.solvd.databases.model.Purchase;
import com.laba.solvd.databases.model.Track;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrackDAO implements ITrackDAO {

  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

  private static final String FIND_QUERY = "SELECT * FROM musicmarketplace.tracks AS t " +
      "JOIN musicmarketplace.purchases AS p ON t.id = p.trackid ";


//  public List<Purchase> getPurchase(int purchaseID) {
//    List<Purchase> purchases = new ArrayList<>();
//    Connection connection = CONNECTION_POOL.getConnectionFromPool();
//    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT purchases.id, purchases.date, purchases.price " +
//        "FROM purchases " +
//        "JOIN track_purchase ON track_purchase.purchase_id = purchases.id " +
//        "JOIN tracks ON tracks.id = track_purchase.track_id " +
//        "WHERE purchases.id = ?")){
//      preparedStatement.setInt(1, purchaseID);
//      ResultSet resultSet = preparedStatement.executeQuery();
//      while (resultSet.next()){
//        Purchase purchase = new Purchase();
//        purchase.setId(resultSet.getInt("id"));
//        purchase.setPurchaseDate(resultSet.getDate("date"));
//        purchase.setPrice(resultSet.getBigDecimal("price"));
//
//        purchases.add(purchase);
//      }
//    } catch (SQLException throwables) {
//      throwables.printStackTrace();
//    }finally{
//      CONNECTION_POOL.releaseConnectionToPool(connection);
//    }
//
//
//    return purchases;
//  }


  public Track getById(int id) throws SQLException {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();

    Track track = new Track();

    Properties properties = new Properties();

    try(InputStream input = new FileInputStream("src/main/resources/db.properties")){
      properties.load(input);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }



    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tracks WHERE ID=?");

    preparedStatement.setInt(1,id);

    ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()){
      track.setId(resultSet.getInt("id"));
      track.setTitle(resultSet.getString("title"));
    }


    return track;
  }

  @Override
  public void create(Track entity, Integer id) {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("Insert into tracks(id, title, duration, albumid) VALUES (?, ?, ?, ?)",
        Statement.RETURN_GENERATED_KEYS)){
      //preparedStatement.setInt(1, entity.getId());
      preparedStatement.setString(2, entity.getTitle());
      preparedStatement.setTime(3,entity.getDuration());
      preparedStatement.setInt(4,id);


      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      while (resultSet.next()){
        entity.setId(resultSet.getInt(1));
      }

    }catch(SQLException e){
      throw new RuntimeException("unable to create user", e);
    }finally {
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }

  }


  @Override
  public void createNoID(Track entity) {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("Insert into tracks(id, title, duration, albumid) VALUES (?, ?, ?, ?)",
        Statement.RETURN_GENERATED_KEYS)){
      //preparedStatement.setInt(1, entity.getId());
      preparedStatement.setString(2, entity.getTitle());
      preparedStatement.setTime(3,entity.getDuration());
      preparedStatement.setInt(4,entity.getAlbum().getId());


      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      while (resultSet.next()){
        entity.setId(resultSet.getInt(1));
      }

    }catch(SQLException e){
      throw new RuntimeException("unable to create user", e);
    }finally {
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }

  }




  @Override
  public List<Track> getAll() {
    List<Track> tracks = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tracks")){
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        Track track = new Track();
        track.setId(resultSet.getInt("id"));
        track.setTitle(resultSet.getString("title"));

        tracks.add(track);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return tracks;
  }

@Override
public List<Track> findAll(){

  List<Track> tracks = new ArrayList<>();

  Connection connection = CONNECTION_POOL.getConnectionFromPool();
  try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY)){
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()){
      Track track = new Track();
      track.setId(resultSet.getInt("id"));
      track.setTitle(resultSet.getString("title"));

      tracks.add(track);
    }
  } catch (SQLException throwables) {
    throwables.printStackTrace();
  }finally{
    CONNECTION_POOL.releaseConnectionToPool(connection);
  }
  return tracks;
}


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

  public int getMaxTrackId() {
    // Add the necessary logic to retrieve the maximum user ID from the database
    int maxId = 0;

    // Retrieve the maximum ID using a database query
    try (Connection connection = CONNECTION_POOL.getConnectionFromPool();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM tracks")) {
      if (resultSet.next()) {
        maxId = resultSet.getInt(1);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to get the maximum track ID", e);
    }

    return maxId;
  }


//  public static void main(String args[]) throws SQLException {
//
//  }
}
