package com.example.Social_App.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class React {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_react;

    @ManyToOne
    @JoinColumn(name = "owner_id_user")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "post_id_post")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id_comment")
    private Comment comment;

    @Enumerated(EnumType.STRING)
    private ReactType reactType;
}
