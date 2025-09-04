package com.svenson95.track_e_backend.database.repository;

import com.svenson95.track_e_backend.database.model.Workout;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkoutRepository extends MongoRepository<Workout, String> {
  Optional<Workout> findByUserId(String userId);
}
