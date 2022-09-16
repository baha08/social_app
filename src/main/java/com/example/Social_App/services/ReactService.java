package com.example.Social_App.services;

import com.example.Social_App.exceptions.ResourceNotFoundException;
import com.example.Social_App.models.Comment;
import com.example.Social_App.models.Post;
import com.example.Social_App.models.React;
import com.example.Social_App.models.User;
import com.example.Social_App.repository.CommentRepository;
import com.example.Social_App.repository.PostRepository;
import com.example.Social_App.repository.ReactRepository;
import com.example.Social_App.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Social_App.constants.ErrorConst.ERROR_MESSAGE;

@Service
@Slf4j
public class ReactService {

    public final ReactRepository reactRepository;

    public final UserRepository userRepository;

    public final PostRepository postRepository;

    public final CommentRepository commentRepository;

    public ReactService(final ReactRepository reactRepository, final UserRepository userRepository, final PostRepository postRepository, final CommentRepository commentRepository) {
        this.reactRepository = reactRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<React> getAllReacts() {
        return reactRepository.findAll();
    }

    public React getReactPerId(final Long idReact) {
        return reactRepository.findById(idReact).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idReact);
            return new ResourceNotFoundException();
        });

    }

    public React createReactForComment(final React react, final Long idUser, final Long idComment) {
        final User user = userRepository.findById(idUser).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idUser);
            return new ResourceNotFoundException();
        });
        final Comment comment = commentRepository.findById(idComment).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idComment);
            return new ResourceNotFoundException();
        });
        react.setComment(comment);
        react.setOwner(user);
        return reactRepository.save(react);
    }

    public React createReactForPost(final React react, final Long idUser, final Long idPost) {
        final User user = userRepository.findById(idUser).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idUser);
            return new ResourceNotFoundException();
        });
        final Post post = postRepository.findById(idPost).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idPost);
            return new ResourceNotFoundException();
        });
        react.setPost(post);
        react.setOwner(user);
        return reactRepository.save(react);
    }

    public React updateReact(final React reactData, final Long idReact) {
        final React react = reactRepository.findById(idReact).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idReact);
            return new ResourceNotFoundException();
        });

        react.setReactType(reactData.getReactType());
        return reactRepository.save(react);
    }

    public void deleteReact(final Long idReact) {
        final React react = reactRepository.findById(idReact).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idReact);
            return new ResourceNotFoundException();
        });
        reactRepository.delete(react);
    }

    public List<React> getReactsPerPost(final Long idPost) {
        final Post post = postRepository.findById(idPost).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idPost);
            return new ResourceNotFoundException();
        });
        return reactRepository.getReactsPerPost(post);
    }

    public List<React> getReactsPerUser(final Long idUser) {
        final User user = userRepository.findById(idUser).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idUser);
            return new ResourceNotFoundException();
        });
        return reactRepository.getReactsPerUser(user);
    }

    public List<React> getReactsPerComment(final Long idComment) {
        final Comment comment = commentRepository.findById(idComment).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idComment);
            return new ResourceNotFoundException();
        });
        return reactRepository.getReactsPerComment(comment);
    }

    public int getReactNumberPerPost(final Long idPost) {
        final Post post = postRepository.findById(idPost).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idPost);
            return new ResourceNotFoundException();
        });
        return reactRepository.getReactNumberPerPost(post);
    }

    public int getReactNumberPerComment(final Long idComment) {
        final Comment comment = commentRepository.findById(idComment).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, idComment);
            return new ResourceNotFoundException();
        });
        return reactRepository.getReactNumberPerComment(comment);
    }
}
