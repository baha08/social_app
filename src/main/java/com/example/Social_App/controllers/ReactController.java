package com.example.Social_App.controllers;

import com.example.Social_App.DTOs.ReactDto;
import com.example.Social_App.mappers.ReactMapper;
import com.example.Social_App.models.React;
import com.example.Social_App.services.ReactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/react")
public class ReactController {


    private final ReactService reactService;

    public ReactController(final ReactService reactService) {
        this.reactService = reactService;
    }

    @GetMapping
    public List<ReactDto> getAllReacts() {
        return reactService.getAllReacts().stream()
                .map(react -> ReactMapper.MAPPER.reactoToDto(react)).collect(Collectors.toList());
    }

    @GetMapping("{id_react}")
    public ResponseEntity<ReactDto> getReactById(@PathVariable(value = "id_react") final Long id) {
        return new ResponseEntity<>(ReactMapper.MAPPER.reactoToDto(reactService.getReactPerId(id)), HttpStatus.OK);
    }

    @PostMapping("/post/{id_user}/{id_post}")
    public ResponseEntity<ReactDto> createReactForPost(@RequestBody final ReactDto reactDto, @PathVariable(value = "id_user") final Long idUser, @PathVariable(value = "id_post") final Long idPost) {
        final React react = ReactMapper.MAPPER.dtoToReact(reactDto);
        return new ResponseEntity<>(ReactMapper.MAPPER.reactoToDto(reactService.createReactForPost(react, idUser, idPost)), HttpStatus.CREATED);
    }

    @PostMapping("/comment/{id_user}/{id_comment}")
    public ResponseEntity<ReactDto> createReactForComment(@RequestBody final ReactDto reactDto, @PathVariable(value = "id_user") final Long idUser, @PathVariable(value = "id_comment") final Long idComment) {
        final React react = ReactMapper.MAPPER.dtoToReact(reactDto);
        return new ResponseEntity<>(ReactMapper.MAPPER.reactoToDto(reactService.createReactForComment(react, idUser, idComment)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReactDto> updateReact(@RequestBody final ReactDto reactDto, @PathVariable final Long id) {
        final React react = ReactMapper.MAPPER.dtoToReact(reactDto);
        return new ResponseEntity<>(ReactMapper.MAPPER.reactoToDto(reactService.updateReact(react, id)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteReact(@PathVariable final Long id) {
        reactService.deleteReact(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/post/{id_post}")
    public List<ReactDto> getReactsPerPost(@PathVariable(value = "id_post") final Long id) {
        return reactService.getReactsPerPost(id).stream()
                .map(react -> ReactMapper.MAPPER.reactoToDto(react)).collect(Collectors.toList());
    }

    @GetMapping("/user/{id_user}")
    public List<ReactDto> getReactsPerUser(@PathVariable(value = "id_user") final Long id) {
        return reactService.getReactsPerUser(id).stream()
                .map(react -> ReactMapper.MAPPER.reactoToDto(react)).collect(Collectors.toList());
    }

    @GetMapping("/comment/{id_comment}")
    public List<ReactDto> getReactsPerComment(@PathVariable(value = "id_comment") final Long id) {
        return reactService.getReactsPerComment(id).stream()
                .map(react -> ReactMapper.MAPPER.reactoToDto(react)).collect(Collectors.toList());
    }

    @GetMapping("/number/comment/{id_comment}")
    public int getReactNumberPerComment(@PathVariable(value = "id_comment") final Long idComment) {
        return reactService.getReactNumberPerComment(idComment);
    }

    @GetMapping("/number/post/{id_post}")
    public int getReactNumberPerPost(@PathVariable(value = "id_post") final Long idPost) {
        return reactService.getReactNumberPerPost(idPost);
    }


}
