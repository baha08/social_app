package com.example.Social_App.DTOs;


import com.example.Social_App.models.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id_user;

    private String firstName;

    private String lastName;

    private String email;

    private Set<Role> roles = new HashSet<>();

}
