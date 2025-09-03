package com.svenson95.track_e_backend.database.repository;

import com.svenson95.track_e_backend.database.model.UserWorkouts;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserWorkoutsRepository extends MongoRepository<UserWorkouts, String> {
  Optional<UserWorkouts> findByUserId(String userId);
}
