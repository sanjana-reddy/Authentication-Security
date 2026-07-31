package com.unitedtekinfo.authsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private String token;

    private String type;

    private String username;

    private String email;
}