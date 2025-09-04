package com.svenson95.track_e_backend.database.repository;

import com.svenson95.track_e_backend.database.model.Workout;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkoutRepository extends MongoRepository<Workout, String> {
  List<Workout> findByUserId(String userId);
}
