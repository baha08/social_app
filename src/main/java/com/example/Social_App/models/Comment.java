package com.example.Social_App.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comment;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "owner_id_user")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "post_id_post")
    private Post post;
    
    private LocalDateTime updatedAT;

    private LocalDateTime createdAt;


}
