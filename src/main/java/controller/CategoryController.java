package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import com.jumpstart.food_ordering_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// The controller handles incoming HTTP requests and returns responses.
// It acts as the entry point for the API and delegates work to the service layer.
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    // Injecting the service so we can call business logic methods.
    @Autowired
    private CategoryService categoryService;

    // GET /api/categories - returns all categories
    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // GET /api/categories/{id} - returns one category by id
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
}