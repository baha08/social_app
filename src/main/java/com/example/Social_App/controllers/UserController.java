package com.example.Social_App.controllers;

import com.example.Social_App.DTOs.UserCreationDto;
import com.example.Social_App.DTOs.UserDto;
import com.example.Social_App.mappers.UserCreationMapper;
import com.example.Social_App.mappers.UserMapper;
import com.example.Social_App.models.User;
import com.example.Social_App.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")

public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(user -> UserMapper.MAPPER.userToDto(user))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable final Long id) {
        return new ResponseEntity<>(UserMapper.MAPPER.userToDto(userService.getUserById(id)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody final UserCreationDto userCreationDto) {
        final User user = UserCreationMapper.MAPPER.dtoToUserc(userCreationDto);
        return new ResponseEntity<>(UserMapper.MAPPER.userToDto(userService.createUser(user)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody final UserCreationDto userCreationDto, @PathVariable final Long id) {
        final User user = UserCreationMapper.MAPPER.dtoToUserc(userCreationDto);
        return new ResponseEntity<>(UserMapper.MAPPER.userToDto(userService.updateUser(id, user)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable final Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
