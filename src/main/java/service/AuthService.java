package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.config.JwtUtils;
import com.jumpstart.food_ordering_system.dto.LoginRequest;
import com.jumpstart.food_ordering_system.dto.LoginResponse;
import com.jumpstart.food_ordering_system.dto.RegisterRequest;
import com.jumpstart.food_ordering_system.entity.Role;
import com.jumpstart.food_ordering_system.entity.User;
import com.jumpstart.food_ordering_system.repository.RoleRepository;
import com.jumpstart.food_ordering_system.repository.UserRepository;
import com.jumpstart.food_ordering_system.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public Response<String> register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return Response.error(400, "Email is already registered");
        }

        // Look up the CUSTOMER role
        Role customerRole = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("CUSTOMER role not found"));

        // Build and save the user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .active(true)
                .roles(List.of(customerRole))
                .build();

        userRepository.save(user);

        return Response.success("Registration successful", null);
    }

    public Response<LoginResponse> login(LoginRequest request) {

        // Look up user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        // Wrong email or wrong password — same message to prevent enumeration
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return Response.error(400, "Invalid credentials");
        }

        // Inactive account
        if (!user.isActive()) {
            return Response.error(400, "Account inactive. Please contact support.");
        }

        // Generate token
        String token = jwtUtils.generateToken(user.getEmail());

        // Build response
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .toList();

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setEmail(user.getEmail());
        loginResponse.setName(user.getName());
        loginResponse.setRoles(roles);

        return Response.success("Login successful", loginResponse);
    }
}