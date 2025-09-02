package com.svenson95.track_e_backend.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.svenson95.track_e_backend.database.model.UserWorkouts;


public interface UserWorkoutsRepository extends MongoRepository<UserWorkouts, String> {
}