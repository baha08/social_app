package com.example.Social_App.services;

import com.example.Social_App.exceptions.ResourceNotFoundException;
import com.example.Social_App.models.Comment;
import com.example.Social_App.models.Post;
import com.example.Social_App.models.User;
import com.example.Social_App.repository.CommentRepository;
import com.example.Social_App.repository.PostRepository;
import com.example.Social_App.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.Social_App.constants.ErrorConst.ERROR_MESSAGE;

@Service
@Slf4j
public class CommentService {


    public final CommentRepository commentRepository;

    public final PostRepository postRepository;

    public final UserRepository userRepository;

    public CommentService(final CommentRepository commentRepository, final PostRepository postRepository, final UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(final Long idComment) {
        return commentRepository.findById(idComment).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idComment);
            return new ResourceNotFoundException();
        });

    }

    public Comment createComment(final Comment comment, final Long idUser, final Long idPost) {
        final User user = userRepository.findById(idUser).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idUser);
            return new ResourceNotFoundException();
        });
        final Post post = postRepository.findById(idPost).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idPost);
            return new ResourceNotFoundException();
        });

        final LocalDateTime dateTime = LocalDateTime.now();
        comment.setCreatedAt(dateTime);
        comment.setOwner(user);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Comment updateComment(final Comment commentData, final Long idComment) {
        final Comment comment = commentRepository.findById(idComment).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idComment);
            return new ResourceNotFoundException();
        });
        final LocalDateTime dateTime = LocalDateTime.now();
        comment.setUpdatedAT(dateTime);
        comment.setContent(commentData.getContent());
        return commentRepository.save(comment);

    }

    public void deleteComment(final Long idComment) {
        final Comment comment = commentRepository.findById(idComment).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idComment);
            return new ResourceNotFoundException();
        });
        commentRepository.delete(comment);
    }


    public List<Comment> getCommentPerPostId(final Long idPost) {
        final Post post = postRepository.findById(idPost).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idPost);
            return new ResourceNotFoundException();
        });
        return commentRepository.getCommentPerPostId(post);

    }
}
