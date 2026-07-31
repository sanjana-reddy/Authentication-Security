package com.unitedtekinfo.authsecurity.service;

import com.unitedtekinfo.authsecurity.dto.LoginRequest;
import com.unitedtekinfo.authsecurity.dto.LoginResponse;
import com.unitedtekinfo.authsecurity.dto.RegisterRequest;
import com.unitedtekinfo.authsecurity.entity.Role;
import com.unitedtekinfo.authsecurity.repository.RoleRepository;
import com.unitedtekinfo.authsecurity.repository.UserRepository;
import com.unitedtekinfo.authsecurity.security.JwtService;
import com.unitedtekinfo.authsecurity.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void registerSuccessTest() {

        RegisterRequest request = new RegisterRequest();

        request.setUsername("registerSuccessTestUser");
        request.setEmail("registerSuccessTestUser@gmail.com");
        request.setPassword("password123");

        when(userRepository.existsByEmail(anyString()))
                .thenReturn(false);

        Role role = new Role();
        role.setName("ROLE_CUSTOMER");

        when(roleRepository.findByName("ROLE_CUSTOMER"))
                .thenReturn(Optional.of(role));

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        String result = authService.register(request);

        assertEquals("User registered successfully", result);

        verify(userRepository).save(any());
    }

    @Test
    void registerDuplicateEmailTest() {

        RegisterRequest request = new RegisterRequest();

        request.setUsername("duplicateUser");
        request.setEmail("duplicate@gmail.com");
        request.setPassword("password123");

        when(userRepository.existsByEmail("duplicate@gmail.com"))
                .thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> authService.register(request));

        assertEquals("Email already exists", exception.getMessage());

        verify(userRepository, never()).save(any());
    }

    @Test
    void loginSuccessTest() {

        LoginRequest request = new LoginRequest();

        request.setEmail("loginSuccessTest@gmail.com");
        request.setPassword("password123");

        UserDetails userDetails = new User(
                "loginSuccessTest@gmail.com",
                "password123",
                Collections.emptyList()
        );

        when(customUserDetailsService.loadUserByUsername("loginSuccessTest@gmail.com"))
                .thenReturn(userDetails);

        when(jwtService.generateToken(userDetails))
                .thenReturn("sample-jwt-token");

        LoginResponse response = authService.login(request);

        assertNotNull(response);

        assertEquals("sample-jwt-token", response.getToken());
        assertEquals("Bearer", response.getType());
        assertEquals("loginSuccessTest@gmail.com", response.getUsername());
        assertEquals("loginSuccessTest@gmail.com", response.getEmail());

        verify(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void loginFailureTest() {

        LoginRequest request = new LoginRequest();

        request.setEmail("loginSuccessTest@gmail.com");
        request.setPassword("wrongpassword");

        doThrow(new BadCredentialsException("Bad credentials"))
                .when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        assertThrows(
                BadCredentialsException.class,
                () -> authService.login(request)
        );
    }

}