package com.example.Social_App.mappers;

import com.example.Social_App.DTOs.CommentDto;
import com.example.Social_App.DTOs.PostDto;
import com.example.Social_App.models.Comment;
import com.example.Social_App.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    public static final CommentMapper MAPPER = Mappers.getMapper(CommentMapper.class);

    public abstract CommentDto commentToDto(Comment comment);

    public abstract Comment dtoToComment(CommentDto commentDto);
}
