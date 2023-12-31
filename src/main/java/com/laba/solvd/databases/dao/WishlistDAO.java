package com.laba.solvd.databases.dao;

import com.laba.solvd.databases.configurations.ConnectionPool;
import com.laba.solvd.databases.interfacedao.IWishlistDAO;
import com.laba.solvd.databases.model.Album;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.Wishlist;
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

public class WishlistDAO implements IWishlistDAO {

  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
  private static final String DELETE = "DELETE FROM Wishlists WHERE id=?";
  private static final String UPDATE = "UPDATE Wishlists SET name =?, userid=?  WHERE id=?";

  public Wishlist getById(int id) throws SQLException {

    Connection connection = CONNECTION_POOL.getConnectionFromPool();

    Wishlist wishlist = new Wishlist();

    Properties properties = new Properties();

    try(InputStream input = new FileInputStream("src/main/resources/db.properties")){
      properties.load(input);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }



      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM wishlists WHERE ID=?");

      preparedStatement.setInt(1,id);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        wishlist.setId(resultSet.getInt("id"));
        wishlist.setName(resultSet.getString("name"));
      }


    return wishlist;

  }

  @Override
  public void create(Wishlist entity) {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("Insert into wishlists (id, name,userid) VALUES (?, ?,?)",
        Statement.RETURN_GENERATED_KEYS)){
      Integer wishlistId = entity.getId();
      if (wishlistId  == null) {
        throw new IllegalArgumentException("wishlistId  cannot be null");
      }
      //preparedStatement.setInt(1, entity.getId());
      preparedStatement.setString(2, entity.getName());
      preparedStatement.setInt(3,entity.getId());


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
  public List<Wishlist> getAll() {
    List<Wishlist> wishlists = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM WISHLISTS")){
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        Wishlist wishlist = new Wishlist();
        wishlist.setId(resultSet.getInt("id"));
        wishlist.setName(resultSet.getString("name"));

        wishlists.add(wishlist);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return wishlists;
  }

  public List<Wishlist> getWishlist(int wishlistId) {

    List<Wishlist> wishlists = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();

    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT wishlists.id, wishlists.username " +
        "FROM wishlists " +
        "JOIN albums_wishlists ON albums_wishlists.wishlist_id = wishlists.id " +
        "WHERE wishlists.id = ?")){
      preparedStatement.setInt(1,wishlistId);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        Wishlist wishlist = new Wishlist();
        wishlist.setId(resultSet.getInt("id"));
        wishlist.setName(resultSet.getString("name"));

        wishlists.add(wishlist);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }

    return wishlists;
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
    Connection connection = CONNECTION_POOL.getConnectionFromPool();

    if(entity == null){
      throw new NullPointerException();
    }

    User user = entity.getUser();
    if (user == null) {
      throw new IllegalArgumentException("User associated with the wishlist cannot be null");
    }
    try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)){
//      preparedStatement.setInt(1, entity.getId());
//      preparedStatement.setString(2, entity.getName());
//      preparedStatement.setInt(3, user.getId());

      preparedStatement.setString(1, entity.getName());
      preparedStatement.setInt(2, entity.getUser().getId());
      preparedStatement.setInt(3, entity.getId());


      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("unable to update album", e);
    }finally {
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }

  }

  @Override
  public void delete(int id) {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    if(id <= 0){
      throw new IllegalArgumentException("id value is invalid");
    }

    try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();
    }catch (SQLException e){
      throw new RuntimeException("unable to delete", e);
    }finally {
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }

  }

  public int getMaxWishlistId() {
    // Add the necessary logic to retrieve the maximum user ID from the database
    int maxId = 0;

    // Retrieve the maximum ID using a database query
    try (Connection connection = CONNECTION_POOL.getConnectionFromPool();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM WISHLISTS")) {
      if (resultSet.next()) {
        maxId = resultSet.getInt(1);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to get the maximum user profile ID", e);
    }

    return maxId;
  }



//  public static void main(String args[]) throws SQLException {
//    Wishlist wishlist = new WishlistDAO().getById(1);
//    System.out.println("Wishlist ID: " + wishlist.getId());
//
//  }


}
