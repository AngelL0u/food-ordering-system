package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import com.jumpstart.food_ordering_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// The controller handles incoming HTTP requests and returns responses.
// It acts as the entry point for the API and delegates work to the service layer.
@RestController
@RequestMapping("/api")
public class CategoryController {

    // Injecting the service so we can call business logic methods.
    @Autowired
    private CategoryService categoryService;

    // This endpoint handles GET requests to /api/category
    // It returns a list of all categories in JSON format.
    @GetMapping("/category")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }
}