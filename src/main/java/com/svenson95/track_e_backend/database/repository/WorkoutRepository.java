package com.svenson95.track_e_backend.database.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.svenson95.track_e_backend.database.model.Workout;

public interface WorkoutRepository extends MongoRepository<Workout, String> {
  Optional<List<Workout>> findByUserId(String userId);
}
