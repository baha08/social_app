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
public class PostDto {
    private Long id_post ;

    @Lob
    private String content;

    private LocalDateTime updatedAT;

    private LocalDateTime createdAt;

    private UserDto owner;
}
