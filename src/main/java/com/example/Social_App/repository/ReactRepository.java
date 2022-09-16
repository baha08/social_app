package com.example.Social_App.repository;

import com.example.Social_App.models.Comment;
import com.example.Social_App.models.Post;
import com.example.Social_App.models.React;
import com.example.Social_App.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactRepository extends JpaRepository<React,Long> {

    @Query("FROM React r WHERE r.post = :post")
    public List<React> getReactsPerPost(Post post);

    @Query("FROM React r WHERE r.owner = :user")
    public List<React> getReactsPerUser (User user);

    @Query("FROM React r WHERE r.comment = :comment")
    public List<React> getReactsPerComment(Comment comment);

    @Query("SELECT COUNT(r) FROM React r WHERE r.post = :post")
    public int getReactNumberPerPost(Post post);

    @Query("SELECT COUNT(r) FROM React r WHERE r.comment = :comment")
    public int getReactNumberPerComment(Comment comment);
}
