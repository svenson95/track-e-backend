package com.svenson95.track_e_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.svenson95.track_e_backend.model.User;


public interface UserRepository extends MongoRepository<User, String> {
}