package com.svenson95.track_e_backend.database.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.svenson95.track_e_backend.database.dto.UserDTO;
import com.svenson95.track_e_backend.database.dto.WorkoutDTO;
import com.svenson95.track_e_backend.database.mapper.UserMapper;
import com.svenson95.track_e_backend.database.model.User;
import com.svenson95.track_e_backend.database.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public List<UserDTO> findAll() {
    return userRepository.findAll().stream().map(userMapper::toDto).toList();
  }

  public UserDTO createUser(UserDTO dto) {
    User user = userMapper.toEntity(dto);
    User saved = userRepository.save(user);
    return userMapper.toDto(saved);
  }

  public void addWorkoutToList(WorkoutDTO workout) {
    Optional<User> user = userRepository.findById(workout.getUserId());
    user.ifPresent(u -> u.getWorkoutIds().add(workout.getWorkoutId()));
    userRepository.save(user.get());
  }

  public User addUserWorkout(String id, Long workoutId) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      User existingUser = user.get();
      List<Long> workoutIds = existingUser.getWorkoutIds();
      workoutIds.add(workoutId);
      existingUser.setWorkoutIds(workoutIds);
      return userRepository.save(existingUser);
    } else {
      throw new RuntimeException("User not found - id: " + id);
    }
  }

  public User editUserWorkoutsSorting(String id, List<Long> workoutIds) {
    Optional<User> user = userRepository.findById(id);
    user.ifPresent(u -> u.setWorkoutIds(workoutIds));
    return userRepository.save(user.get());
  }

  public void removeWorkoutFromList(String userId, Long workoutId) {
    userRepository
        .findById(userId)
        .ifPresent(
            u -> {
              if (u.getWorkoutIds().remove(workoutId)) {
                userRepository.save(u);
              }
            });
  }
}
