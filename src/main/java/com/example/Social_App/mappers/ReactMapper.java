package com.example.Social_App.mappers;

import com.example.Social_App.DTOs.ReactDto;
import com.example.Social_App.DTOs.UserDto;
import com.example.Social_App.models.React;
import com.example.Social_App.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ReactMapper {


    public static final ReactMapper MAPPER = Mappers.getMapper(ReactMapper.class);

    public abstract ReactDto reactoToDto(React react);

    public abstract React dtoToReact(ReactDto reactDto);
}
