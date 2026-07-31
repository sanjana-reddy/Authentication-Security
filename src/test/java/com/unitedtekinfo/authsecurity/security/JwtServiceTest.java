package com.unitedtekinfo.authsecurity.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {

        jwtService = new JwtService();

        ReflectionTestUtils.setField(jwtService,
                "secretKey",
                "MySecretKeyForJwtAuthenticationProject2026SpringBootSecurity");

        ReflectionTestUtils.setField(jwtService,
                "jwtExpiration",
                86400000L);
    }

    @Test
    void generateTokenTest() {

        User user = new User(
                "generateTokenTestUser@gmail.com",
                "password",
                java.util.Collections.emptyList());

        String token = jwtService.generateToken(user);

        assertNotNull(token);

        assertFalse(token.isEmpty());
    }

    @Test
    void extractUsernameTest() {

        User user = new User(
                "extractUsernameTestuser@gmail.com",
                "password",
                java.util.Collections.emptyList());

        String token = jwtService.generateToken(user);

        String username =
                jwtService.extractUsername(token);

        assertEquals("extractUsernameTestuser@gmail.com",
                username);
    }

    @Test
    void validateTokenTest() {

        User user = new User(
                "validateTokenTestUser@gmail.com",
                "password",
                java.util.Collections.emptyList());

        String token =
                jwtService.generateToken(user);

        assertTrue(
                jwtService.isTokenValid(token, user));
    }

}