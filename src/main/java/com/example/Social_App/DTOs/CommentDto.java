package com.example.Social_App.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id_comment;

    @Lob
    private String content;


    private LocalDateTime updatedAT;

    private LocalDateTime createdAt;

    private UserDto owner;

    private PostDto post;
}
