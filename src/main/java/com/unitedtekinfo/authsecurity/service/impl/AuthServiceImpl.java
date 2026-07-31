package com.unitedtekinfo.authsecurity.service.impl;

import com.unitedtekinfo.authsecurity.dto.LoginRequest;
import com.unitedtekinfo.authsecurity.dto.LoginResponse;
import com.unitedtekinfo.authsecurity.dto.RegisterRequest;
import com.unitedtekinfo.authsecurity.entity.Role;
import com.unitedtekinfo.authsecurity.entity.User;
import com.unitedtekinfo.authsecurity.repository.RoleRepository;
import com.unitedtekinfo.authsecurity.repository.UserRepository;
import com.unitedtekinfo.authsecurity.service.CustomUserDetailsService;
import com.unitedtekinfo.authsecurity.security.JwtService;
import com.unitedtekinfo.authsecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role customerRole = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roles.add(customerRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(request.getEmail());

        String token = jwtService.generateToken(userDetails);

        return new LoginResponse(
                token,
                "Bearer",
                userDetails.getUsername(),
                request.getEmail()
        );
    }
}