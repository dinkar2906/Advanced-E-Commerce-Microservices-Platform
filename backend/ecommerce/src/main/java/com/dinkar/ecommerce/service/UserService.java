package com.dinkar.ecommerce.service;

import com.dinkar.ecommerce.dto.RegisterRequest;
import com.dinkar.ecommerce.dto.UserResponse;
import com.dinkar.ecommerce.entity.Role;
import com.dinkar.ecommerce.entity.User;
import com.dinkar.ecommerce.exception.ProductNotFoundException;
import com.dinkar.ecommerce.exception.UserAlreadyExistsException;
import com.dinkar.ecommerce.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dinkar.ecommerce.exception.UserNotFoundException;

import java.util.List;

@Service
public class UserService {

//    private final UserRepository userRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public UserResponse registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                passwordEncoder.encode(
                        request.getPassword()
                )
        )
                .role(Role.USER)
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        )
                );

        return mapToResponse(user);
    }
}