package com.laba.solvd.databases.model;

import java.sql.Time;
import java.util.List;
import java.util.Objects;
import org.apache.ibatis.javassist.NotFoundException;

public class Track {

  private Integer id;

  private String title;

  private Time duration;

  private List<Purchase> purchase;

  private Album album;


  public Track() {
  }

  public Track(Integer id, String title, Time duration,
      List<Purchase> purchase) {
    this.id = id;
    this.title = title;
    this.duration = duration;
    this.purchase = purchase;
  }

  public Track(Integer id, String title, Time duration,
      List<Purchase> purchase, Album album) {
    this.id = id;
    this.title = title;
    this.duration = duration;
    this.purchase = purchase;
    this.album = album;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Time getDuration() {
    return duration;
  }

  public void setDuration(Time duration) {
    this.duration = duration;
  }

  public List<Purchase> getPurchase() {
    return purchase;
  }

  public void setPurchase(List<Purchase> purchase) {
    this.purchase = purchase;
  }

  public Album getAlbum() {
    return album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }

  public Album retrieveAlbumById(int albumId) throws NotFoundException {
    Album album1 = new Album();
    album1.setId(albumId);


    if (album1 == null) {
      // Handle the case when the UserProfile with the specified ID is not found
      throw new NotFoundException("Album not found for ID: " + albumId);
    }
    // Return the retrieved UserProfile
    return album1;
  }

  public void setAlbum(Integer album) throws NotFoundException {
    Album al = retrieveAlbumById(album);
    this.album = al;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Track)) {
      return false;
    }
    Track track = (Track) o;
    return getId().equals(track.getId()) && getTitle().equals(track.getTitle()) && getDuration()
        .equals(track.getDuration()) && getPurchase().equals(track.getPurchase()) && getAlbum()
        .equals(track.getAlbum());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getDuration(), getPurchase(), getAlbum());
  }

  @Override
  public String toString() {
    return "Track{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", duration=" + duration +
        ", purchase=" + purchase +
        ", album=" + album +
        '}';
  }
}
