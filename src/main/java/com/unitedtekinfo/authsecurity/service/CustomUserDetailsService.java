package com.unitedtekinfo.authsecurity.service;

import com.unitedtekinfo.authsecurity.entity.User;
import com.unitedtekinfo.authsecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(

                user.getEmail(),

                user.getPassword(),

                user.getRoles()
                        .stream()
                        .map(role ->
                                new org.springframework.security.core.authority.SimpleGrantedAuthority(
                                        role.getName()))
                        .collect(Collectors.toList())

        );
    }
}