package com.example.Social_App.mappers;

import com.example.Social_App.DTOs.PostDto;
import com.example.Social_App.DTOs.ReactDto;
import com.example.Social_App.models.Post;
import com.example.Social_App.models.React;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class PostMapper {


    public static final PostMapper MAPPER = Mappers.getMapper(PostMapper.class);

    public abstract PostDto postToDto(Post post);

    public abstract Post dtoToPost(PostDto postDto);
}
