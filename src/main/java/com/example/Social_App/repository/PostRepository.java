package com.example.Social_App.repository;

import com.example.Social_App.models.Post;
import com.example.Social_App.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("FROM Post p WHERE p.owner = :user")
    public List<Post> finPostPerUser(User user);
}
