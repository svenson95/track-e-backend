package com.svenson95.track_e_backend.database.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.svenson95.track_e_backend.database.dto.WorkoutDTO;
import com.svenson95.track_e_backend.database.model.Workout;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
  WorkoutDTO toDto(Workout workout);

  Workout toEntity(WorkoutDTO dto);

  List<WorkoutDTO> toDtoList(List<Workout> workouts);

  List<Workout> toEntityList(List<WorkoutDTO> dtos);
}
