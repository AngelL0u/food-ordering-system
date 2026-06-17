package com.jumpstart.food_ordering_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// A DTO (Data Transfer Object) is used to carry data between layers of the application.
// It prevents exposing the entity directly to the outside world.
public class CategoryDto {

    private Long id;

    // Validation: name cannot be blank and must be between 2 and 50 characters
    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 characters")
    private String name;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}