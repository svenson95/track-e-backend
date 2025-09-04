package com.svenson95.track_e_backend.database.controller;

import com.svenson95.track_e_backend.database.model.User;
import com.svenson95.track_e_backend.database.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired private UserRepository userRepository;

  @GetMapping("/")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @PostMapping("/add")
  public User addUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  @PutMapping("/edit/{id}/add-workout/{workoutId}")
  public User editUser(@PathVariable String id, @PathVariable Long workoutId) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      User existingUser = user.get();
      List<Long> workoutIds = existingUser.getWorkoutIds();
      workoutIds.add(workoutId);
      existingUser.setWorkoutIds(workoutIds);
      return userRepository.save(existingUser);
    } else {
      throw new RuntimeException("User not found - id: " + id);
    }
  }
}
