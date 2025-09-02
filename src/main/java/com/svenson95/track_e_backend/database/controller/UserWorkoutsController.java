package com.svenson95.track_e_backend.database.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svenson95.track_e_backend.database.model.UserWorkouts;
import com.svenson95.track_e_backend.database.model.UserWorkouts.WorkoutData;
import com.svenson95.track_e_backend.database.repository.UserWorkoutsRepository;

@RestController
@RequestMapping("/api/user-workouts")
public class UserWorkoutsController {

    @Autowired
    private UserWorkoutsRepository userWorkoutsRepository;

    @GetMapping("/id/{userId}")
    public UserWorkouts getUserWorkouts(@PathVariable String userId) {
        return userWorkoutsRepository.findByUserId(userId)
        .orElseGet(() -> {
            UserWorkouts newUserWorkouts = new UserWorkouts();
            newUserWorkouts.setUserId(userId);
            newUserWorkouts.setWorkouts(new java.util.ArrayList<>());
            return userWorkoutsRepository.save(newUserWorkouts);
        });
    }

    @PostMapping("/add/{userId}")
    public WorkoutData addUserWorkouts(@PathVariable String userId, @RequestBody WorkoutData workoutData) {
        UserWorkouts userWorkouts = userWorkoutsRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("UserWorkouts not found - userId: " + userId));
        userWorkouts.getWorkouts().add(workoutData);
        userWorkoutsRepository.save(userWorkouts);
        return workoutData;
    }

    @PutMapping("/edit/{id}")
    public UserWorkouts editUserWorkouts(@PathVariable String id, @RequestBody UserWorkouts newUserWorkouts) {
        Optional<UserWorkouts> optionalWorkouts = userWorkoutsRepository.findById(id);
        if (optionalWorkouts.isPresent()) {
            UserWorkouts existingWorkouts = optionalWorkouts.get();
            existingWorkouts.setUserId(newUserWorkouts.getUserId());
            existingWorkouts.setWorkouts(newUserWorkouts.getWorkouts());
            return userWorkoutsRepository.save(existingWorkouts);
        } else {
            throw new RuntimeException("UserWorkouts not found - id: " + id);
        }
    }
}
