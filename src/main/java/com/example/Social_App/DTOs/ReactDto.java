package com.example.Social_App.DTOs;


import com.example.Social_App.models.ReactType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReactDto {
    private Long id_react;

    private UserDto owner;

    private PostDto post;

    private CommentDto comment;

    @Enumerated(EnumType.STRING)
    private ReactType reactType;
}
