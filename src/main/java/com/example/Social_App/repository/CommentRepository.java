package com.example.Social_App.repository;

import com.example.Social_App.models.Comment;
import com.example.Social_App.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("FROM Comment c WHERE c.post = :post")
    public List<Comment> getCommentPerPostId(Post post);
    
}
