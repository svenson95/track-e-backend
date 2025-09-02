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
import com.svenson95.track_e_backend.database.repository.UserWorkoutsRepository;

@RestController
@RequestMapping("/api/user-workouts")
public class UserWorkoutsController {

    @Autowired
    private UserWorkoutsRepository userWorkoutsRepository;

    @GetMapping("/id/{id}")
    public UserWorkouts getUserWorkouts(@PathVariable String id) {
        return userWorkoutsRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("UserWorkouts not found - id: " + id));
    }

    @PostMapping("/add")
    public UserWorkouts addUserWorkouts(@RequestBody UserWorkouts userWorkouts) {
        return userWorkoutsRepository.save(userWorkouts);
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
