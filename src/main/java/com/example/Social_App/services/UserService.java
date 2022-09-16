package com.example.Social_App.services;

import com.example.Social_App.exceptions.ResourceNotFoundException;
import com.example.Social_App.models.Role;
import com.example.Social_App.models.User;
import com.example.Social_App.repository.RoleRepository;
import com.example.Social_App.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static com.example.Social_App.constants.ErrorConst.ERROR_MESSAGE;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    public final UserRepository userRepository;
    public final RoleRepository roleRepository;
    public final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email);
        if (user == null) {
            log.error("user not found in the database");
            throw new UsernameNotFoundException("user not found in the database");
        } else {
            log.info("user  found in the database : {}", email);
        }
        final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(final User user) {
        final Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>());
        user.addRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });
    }

    public User updateUser(final Long id, final User userDetails) {

        final User user = userRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });

        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(final Long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> {
            log.error(ERROR_MESSAGE, id);
            return new ResourceNotFoundException();
        });
        userRepository.delete(user);
    }


}

