package com.svenson95.track_e_backend.database.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.svenson95.track_e_backend.database.model.User;

public interface UserRepository extends MongoRepository<User, String> {
  boolean existsByGoogleId(String googleId);

  Optional<User> findByGoogleId(String googleId);
}
