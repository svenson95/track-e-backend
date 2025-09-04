package com.svenson95.track_e_backend.database.controller;

import com.svenson95.track_e_backend.database.model.User;
import com.svenson95.track_e_backend.database.model.Workout;
import com.svenson95.track_e_backend.database.repository.UserRepository;
import com.svenson95.track_e_backend.database.repository.WorkoutRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

  @Autowired private WorkoutRepository workoutsRepository;
  @Autowired private UserRepository usersRepository;

  @GetMapping("/get/{userId}")
  public List<Workout> getWorkouts(@PathVariable String userId) {
    return workoutsRepository
        .findByUserId(userId)
        .map(Collections::singletonList)
        .orElse(Collections.emptyList());
  }

  @PostMapping("/add/{userId}")
  public Workout addUserWorkouts(@PathVariable String userId, @RequestBody Workout workout) {
    Optional<User> user = usersRepository.findById(userId);
    if (user.isEmpty()) {
      throw new RuntimeException("User not found - id: " + userId);
    }
    Workout savedWorkout = workoutsRepository.save(workout);
    user.get().getWorkouts().add(savedWorkout.getId());
    usersRepository.save(user.get());

    return savedWorkout;
  }

  // @PutMapping("/edit/{id}")
  // public Workout editUserWorkouts(@PathVariable String id, @RequestBody Workout newUserWorkouts)
  // {
  //   Optional<Workout> optionalWorkouts = workoutsRepository.findById(id);
  //   if (optionalWorkouts.isPresent()) {
  //     Workout existingWorkouts = optionalWorkouts.get();
  //     existingWorkouts.setUserId(newUserWorkouts.getUserId());
  //     existingWorkouts.setWorkouts(newUserWorkouts.getWorkouts());
  //     return workoutsRepository.save(existingWorkouts);
  //   } else {
  //     throw new RuntimeException("UserWorkouts not found - id: " + id);
  //   }
  // }
}
