package com.example.Social_App.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post ;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "owner_id_user")
    private User owner ;

    private LocalDateTime updatedAT;

    private LocalDateTime createdAt;







}
