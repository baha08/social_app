package com.example.Social_App.controllers;

import com.example.Social_App.DTOs.PostDto;
import com.example.Social_App.mappers.PostMapper;
import com.example.Social_App.models.Post;
import com.example.Social_App.services.PostService;
import com.example.Social_App.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {


    private final PostService postService;
    private final UserService userService;

    public PostController(final PostService postService, final UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts().stream()
                .map(post -> PostMapper.MAPPER.postToDto(post)).collect(Collectors.toList());
    }

    @GetMapping("{id_post}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(value = "id_post") final Long id) {
        return new ResponseEntity<>(PostMapper.MAPPER.postToDto(postService.getPostById(id)), HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<PostDto> createPost(@RequestBody final PostDto postDto, @PathVariable(value = "id") final Long idUser) {
        final Post post = PostMapper.MAPPER.dtoToPost(postDto);
        return new ResponseEntity<>(PostMapper.MAPPER.postToDto(postService.createPost(post, idUser)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody final PostDto postDto, @PathVariable final Long id) {
        final Post post = PostMapper.MAPPER.dtoToPost(postDto);
        return new ResponseEntity<>(PostMapper.MAPPER.postToDto(postService.updatePost(post, id)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePost(@PathVariable final Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id_user}")
    public List<PostDto> getPostPerUser(@PathVariable(value = "id_user") final Long id) {
        return postService.findPostPerUser(id).stream()
                .map(post -> PostMapper.MAPPER.postToDto(post)).collect(Collectors.toList());
    }

}
