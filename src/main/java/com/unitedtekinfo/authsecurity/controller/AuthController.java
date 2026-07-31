package com.unitedtekinfo.authsecurity.controller;

import com.unitedtekinfo.authsecurity.dto.ApiResponse;
import com.unitedtekinfo.authsecurity.dto.LoginRequest;
import com.unitedtekinfo.authsecurity.dto.LoginResponse;
import com.unitedtekinfo.authsecurity.dto.RegisterRequest;
import com.unitedtekinfo.authsecurity.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthController {

    private final AuthService authService;
    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ApiResponse register(@Valid @RequestBody RegisterRequest request) {

        String message = authService.register(request);

        return new ApiResponse(message);
    }

    @PostMapping("/login")
    @Operation(summary = "Login user and generate JWT token")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}