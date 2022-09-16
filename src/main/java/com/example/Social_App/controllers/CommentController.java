package com.example.Social_App.controllers;

import com.example.Social_App.DTOs.CommentDto;
import com.example.Social_App.mappers.CommentMapper;
import com.example.Social_App.models.Comment;
import com.example.Social_App.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping
    public List<CommentDto> getAllComments() {
        return commentService.getComments().stream()
                .map(CommentMapper.MAPPER::commentToDto).collect(Collectors.toList());
    }

    @GetMapping("{id_comment}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "id_comment") final Long id) {
        return new ResponseEntity<>(CommentMapper.MAPPER.commentToDto(commentService.getCommentById(id)), HttpStatus.OK);
    }

    @PostMapping("{id_post}/{id_user}")
    public ResponseEntity<CommentDto> createComment(@RequestBody final CommentDto commentDto, @PathVariable(value = "id_post") final Long idPost, @PathVariable(value = "id_user") final Long idUser) {
        final Comment comment = CommentMapper.MAPPER.dtoToComment(commentDto);
        return new ResponseEntity<>(CommentMapper.MAPPER.commentToDto(commentService.createComment(comment, idUser, idPost)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody final CommentDto commentDto, @PathVariable final Long id) {
        final Comment comment = CommentMapper.MAPPER.dtoToComment(commentDto);
        return new ResponseEntity<>(CommentMapper.MAPPER.commentToDto(commentService.updateComment(comment, id)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable final Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/post/{id_post}")
    public List<CommentDto> getPostPerPostId(@PathVariable(value = "id_post") final Long id) {
        return commentService.getCommentPerPostId(id).stream()
                .map(CommentMapper.MAPPER::commentToDto).collect(Collectors.toList());
    }

}
