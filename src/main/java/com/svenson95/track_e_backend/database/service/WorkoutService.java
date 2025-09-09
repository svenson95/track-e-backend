package com.svenson95.track_e_backend.database.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.svenson95.track_e_backend.database.dto.WorkoutDTO;
import com.svenson95.track_e_backend.database.mapper.WorkoutMapper;
import com.svenson95.track_e_backend.database.model.Workout;
import com.svenson95.track_e_backend.database.repository.WorkoutRepository;

@Service
public class WorkoutService {

  private final WorkoutRepository workoutRepository;
  private final WorkoutMapper workoutMapper;
  private final UserService userService;

  public WorkoutService(
      WorkoutRepository workoutRepository, WorkoutMapper workoutMapper, UserService userService) {
    this.workoutRepository = workoutRepository;
    this.workoutMapper = workoutMapper;
    this.userService = userService;
  }

  public List<WorkoutDTO> findUserWorkouts(String userId) {
    return workoutRepository.findByUserId(userId).orElse(List.of()).stream()
        .map(workoutMapper::toDto)
        .toList();
  }

  public WorkoutDTO createWorkout(WorkoutDTO dto) {
    Workout workout = workoutMapper.toEntity(dto);
    Workout saved = workoutRepository.save(workout);
    userService.addWorkoutToList(dto);
    return workoutMapper.toDto(saved);
  }

  public ResponseEntity<Object> deleteById(String id) {
    return workoutRepository
        .findById(id)
        .map(
            workout -> {
              String userId = workout.getUserId();
              workoutRepository.deleteById(id);
              userService.removeWorkoutFromList(userId, workout.getWorkoutId());
              return ResponseEntity.noContent().build(); // 204
            })
        .orElseGet(() -> ResponseEntity.notFound().build()); // 404
  }
}
