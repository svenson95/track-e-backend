package com.svenson95.track_e_backend.database.service;

import com.svenson95.track_e_backend.database.model.User;
import com.svenson95.track_e_backend.database.model.Workout;
import com.svenson95.track_e_backend.database.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;

  public void addWorkoutToList(Workout workout) {
    Optional<User> user = userRepository.findById(workout.getUserId());
    user.ifPresent(u -> u.getWorkoutIds().add(workout.getWorkoutId()));
    userRepository.save(user.get());
  }

  public void removeWorkoutFromList(String userId, Long workoutId) {
    userRepository
        .findById(userId)
        .ifPresent(
            u -> {
              if (u.getWorkoutIds().remove(workoutId)) {
                userRepository.save(u);
              }
            });
  }
}
