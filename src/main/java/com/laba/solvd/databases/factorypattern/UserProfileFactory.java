package com.laba.solvd.databases.factorypattern;

import com.laba.solvd.databases.model.UserProfile;

public class UserProfileFactory {

  public static UserProfile createUserProfile(Integer id, String bio, String profileImage, String location) {
    return new UserProfile(id, bio, profileImage, location);
  }

}
