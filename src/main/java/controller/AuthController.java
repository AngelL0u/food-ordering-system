package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.LoginRequest;
import com.jumpstart.food_ordering_system.dto.LoginResponse;
import com.jumpstart.food_ordering_system.dto.RegisterRequest;
import com.jumpstart.food_ordering_system.response.Response;
import com.jumpstart.food_ordering_system.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // POST /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<Response<String>> register(
            @RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> login(
            @RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}