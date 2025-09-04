package com.svenson95.track_e_backend.auth.service;

import com.svenson95.track_e_backend.database.model.User;
import com.svenson95.track_e_backend.database.repository.UserRepository;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseService {
  @Autowired private UserRepository userRepository;

  public User findOrCreateUser(Map<String, Object> userInfo) {
    String id = (String) userInfo.get("userId");
    return userRepository.findByGoogleId(id).orElseGet(() -> this.createNewUser(userInfo));
  }

  public User createNewUser(Map<String, Object> userInfo) {
    User newUser = new User();
    newUser.setGoogleId((String) userInfo.get("userId"));
    newUser.setEmail((String) userInfo.get("email"));
    newUser.setName((String) userInfo.get("name"));
    newUser.setPicture((String) userInfo.get("picture"));
    newUser.setWeight(0);
    newUser.setHeight(0);
    newUser.setWorkoutIds(new ArrayList<>());
    return userRepository.save(newUser);
  }
}
