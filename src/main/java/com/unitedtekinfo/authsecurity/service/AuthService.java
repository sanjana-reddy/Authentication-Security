package com.unitedtekinfo.authsecurity.service;

import com.unitedtekinfo.authsecurity.dto.LoginRequest;
import com.unitedtekinfo.authsecurity.dto.LoginResponse;
import com.unitedtekinfo.authsecurity.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}