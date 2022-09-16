package com.example.Social_App.mappers;

import com.example.Social_App.DTOs.UserDto;
import com.example.Social_App.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {


    public static final UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    public abstract UserDto userToDto(User user);

    public abstract User dtoToUser(UserDto userDto);

}
