package com.example.Social_App.services;

import com.example.Social_App.exceptions.ResourceNotFoundException;
import com.example.Social_App.models.Role;
import com.example.Social_App.models.User;
import com.example.Social_App.repository.RoleRepository;
import com.example.Social_App.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    public final RoleRepository roleRepository;
    public final UserRepository userRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role createRole(final Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleById(final Long id) {
        return roleRepository.findById(id).orElseThrow(() -> {
            log.error("role don't exist with id {}", id);
            return new ResourceNotFoundException();
        });
    }

    public void addRoleToUser(final String email, final String roleName) {
        final User user = userRepository.findByEmail(email);
        final Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
        log.info("role {} added to user {}", role, user);
    }
}
