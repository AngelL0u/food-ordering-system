package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.exception.CategoryNotFoundException;
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

    @Override
    public CategoryDto getCategoryById(Long id) {

        // Find the category by id, throw exception if not found
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        // Convert entity to DTO and return
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());

        return dto;
    }

    @Override
    public CategoryDto addCategory(CategoryDto dto) {

        // Map DTO to entity
        Category category = new Category();
        category.setName(dto.getName());

        // Save to database
        Category savedCategory = categoryRepository.save(category);

        // Map saved entity back to DTO and return
        CategoryDto responseDto = new CategoryDto();
        responseDto.setId(savedCategory.getId());
        responseDto.setName(savedCategory.getName());

        return responseDto;
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {

        // Find the existing category or throw exception if not found
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        // Update the name
        category.setName(dto.getName());

        // Save updated category
        Category updatedCategory = categoryRepository.save(category);

        // Map to DTO and return
        CategoryDto responseDto = new CategoryDto();
        responseDto.setId(updatedCategory.getId());
        responseDto.setName(updatedCategory.getName());

        return responseDto;
    }
}