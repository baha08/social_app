package com.example.Social_App.mappers;

import com.example.Social_App.DTOs.UserCreationDto;
import com.example.Social_App.DTOs.UserDto;
import com.example.Social_App.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserCreationMapper {
    public static final UserCreationMapper MAPPER = Mappers.getMapper(UserCreationMapper.class);

    public abstract UserCreationDto usercToDto(User user);

    public abstract User dtoToUserc(UserCreationDto userCreationDto);

}
