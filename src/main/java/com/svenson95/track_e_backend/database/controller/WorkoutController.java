package com.svenson95.track_e_backend.database.controller;

import com.svenson95.track_e_backend.database.model.Workout;
import com.svenson95.track_e_backend.database.repository.WorkoutRepository;
import com.svenson95.track_e_backend.database.service.UserService;
import java.util.Collections;
import java.util.List;
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
  @Autowired private UserService userService;

  @GetMapping("/get/{userId}")
  public List<Workout> getWorkouts(@PathVariable String userId) {
    List<Workout> workouts = workoutsRepository.findByUserId(userId).get();

    if (workouts == null || workouts.isEmpty()) {
      return Collections.emptyList();
    } else if (workouts.size() == 1) {
      return Collections.singletonList(workouts.get(0));
    } else {
      return workouts;
    }
  }

  @PostMapping("/add")
  public Workout addWorkout(@RequestBody Workout workout) {
    userService.addWorkoutToList(workout);
    return workoutsRepository.save(workout);
  }

  // @PutMapping("/edit/{id}")
  // public Workout editWorkout(@PathVariable String id, @RequestBody Workout newWorkout)
  // {
  //   Optional<Workout> optionalWorkouts = workoutsRepository.findById(id);
  //   if (optionalWorkouts.isPresent()) {
  //     Workout existingWorkouts = optionalWorkouts.get();
  //     existingWorkouts.setUserId(newWorkout.getUserId());
  //     existingWorkouts.setWorkouts(newWorkout.getWorkouts());
  //     return workoutsRepository.save(existingWorkouts);
  //   } else {
  //     throw new RuntimeException("UserWorkouts not found - id: " + id);
  //   }
  // }
}
