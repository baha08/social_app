package com.example.Social_App.controllers;

import com.example.Social_App.models.Role;
import com.example.Social_App.services.RoleService;
import com.example.Social_App.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    public final RoleService roleService;
    public final UserService userService;

    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody final Role role) {
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
    }

    @PostMapping("/affect")
    public ResponseEntity<?> addRoleToUser(@RequestBody final RoleToUserForm form) {
        roleService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

}

@Data
class RoleToUserForm {
    private String email;
    private String roleName;
}
