package com.svenson95.track_e_backend.database.mapper;

import com.svenson95.track_e_backend.database.dto.UserDTO;
import com.svenson95.track_e_backend.database.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDTO toDto(User workout);

  User toEntity(UserDTO dto);

  List<UserDTO> toDtoList(List<User> workouts);

  List<User> toEntityList(List<UserDTO> dtos);
}
