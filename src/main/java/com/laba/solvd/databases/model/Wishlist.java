package com.laba.solvd.databases.model;

import java.util.List;
import java.util.Objects;
import org.apache.ibatis.javassist.NotFoundException;

public class Wishlist {

  private Integer id;

  private String name;

  private List<Album> albumList;

  private User user;



  public Wishlist() {
  }

  public Wishlist(Integer id, String name, List<Album> albumList) {
    this.id = id;
    this.name = name;
    this.albumList = albumList;
  }

  public Wishlist(Integer id, String name,
      List<Album> albumList, User user) {
    this.id = id;
    this.name = name;
    this.albumList = albumList;
    this.user = user;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Album> getAlbumList() {
    return albumList;
  }

  public void setAlbumList(List<Album> albumList) {
    this.albumList = albumList;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User userid) {
    this.user = userid;
  }

  public User retrieveUserById(int userId) throws NotFoundException {
    User user = new User();
    user.setId(userId);


    if (user == null) {
      // Handle the case when the UserProfile with the specified ID is not found
      throw new NotFoundException("UserProfile not found for ID: " + userId);
    }
    // Return the retrieved UserProfile
    return user;
  }

  public void setUser(Integer userid) throws NotFoundException {
    User userP = retrieveUserById(userid);
    this.user = userP;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Wishlist)) {
      return false;
    }
    Wishlist wishlist = (Wishlist) o;
    return getId().equals(wishlist.getId()) && getName().equals(wishlist.getName())
        && getAlbumList()
        .equals(wishlist.getAlbumList()) && getUser().equals(wishlist.getUser());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getAlbumList(), getUser());
  }

  @Override
  public String toString() {
    return "Wishlist{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", albumList=" + albumList +
        ", user=" + user +
        '}';
  }
}
