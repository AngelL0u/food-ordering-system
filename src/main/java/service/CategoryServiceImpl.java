package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// The service implementation contains the actual business logic.
// It retrieves data from the repository and converts entities into DTOs.
@Service
public class CategoryServiceImpl implements CategoryService {

    // Injecting the repository so we can access the database.
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {

        // Fetch all Category entities from the database
        List<Category> categories = categoryRepository.findAll();

        // Create a list to hold the DTOs
        List<CategoryDto> categoryDtos = new ArrayList<>();

        // Convert each Category entity into a CategoryDto
        for (Category category : categories) {
            CategoryDto dto = new CategoryDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            categoryDtos.add(dto);
        }

        return categoryDtos;
    }
}