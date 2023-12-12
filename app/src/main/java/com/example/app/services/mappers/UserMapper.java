package com.example.app.services.mappers;

import com.example.app.entities.User;
import com.example.app.services.dtos.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "type", constant = "NONE")
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
}
