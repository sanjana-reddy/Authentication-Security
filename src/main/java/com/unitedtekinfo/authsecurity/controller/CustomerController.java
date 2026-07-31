package com.unitedtekinfo.authsecurity.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer", description = "Customer APIs")
public class CustomerController {

    @GetMapping
    @Operation(summary = "Customer Dashboard")
    public String customer() {
        return "Welcome Customer!";
    }
}
