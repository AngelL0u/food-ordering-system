package com.jumpstart.food_ordering_system.config;

import com.jumpstart.food_ordering_system.entity.Role;
import com.jumpstart.food_ordering_system.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Runs on startup and seeds the ADMIN and CUSTOMER roles if they don't exist
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        seedRole("ADMIN");
        seedRole("CUSTOMER");
    }

    private void seedRole(String name) {
        if (roleRepository.findByName(name).isEmpty()) {
            roleRepository.save(Role.builder().name(name).build());
            System.out.println("Seeded role: " + name);
        }
    }
}