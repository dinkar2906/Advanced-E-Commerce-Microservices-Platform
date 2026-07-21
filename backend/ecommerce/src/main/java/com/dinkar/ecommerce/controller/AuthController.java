package com.dinkar.ecommerce.controller;

import com.dinkar.ecommerce.dto.AuthResponse;
import com.dinkar.ecommerce.dto.LoginRequest;
import com.dinkar.ecommerce.dto.RegisterRequest;
import com.dinkar.ecommerce.dto.UserResponse;
import com.dinkar.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid
            @RequestBody RegisterRequest request
    ) {

        UserResponse response = userService.registerUser(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid
            @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(
                userService.login(request)
        );
    }
}