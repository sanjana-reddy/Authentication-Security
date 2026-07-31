package com.unitedtekinfo.authsecurity.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
@Tag(name = "Manager", description = "Manager APIs")
public class ManagerController {

    @GetMapping
    @Operation(summary = "Manager Dashboard")
    public String manager() {
        return "Welcome Manager!";
    }
}

