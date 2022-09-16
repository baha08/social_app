package com.example.Social_App.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreationDto {
    private  String firstName;

    private  String lastName;

    private String email;

    private String password;
}
