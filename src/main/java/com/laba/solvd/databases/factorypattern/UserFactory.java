package com.laba.solvd.databases.factorypattern;

import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.Purchase;
import com.laba.solvd.databases.model.Review;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import com.laba.solvd.databases.model.Wishlist;
import java.util.List;

public class UserFactory {

  public static User createUser(Integer id, String name, String email, String password,
      UserProfile userProfile, List<Purchase> purchaseList,
      List<Playlist> playlistList, List<Review> reviewList,
      List<Wishlist> wishlistList) {
    return new User(id, name, email, password, userProfile, purchaseList, playlistList, reviewList, wishlistList);
  }
}




