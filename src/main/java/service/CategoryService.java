package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.CategoryDto;
import java.util.List;

// The service interface defines the contract for the business logic layer.
// Any class that implements this interface must provide the getAllCategories method.
public interface CategoryService {

    List<CategoryDto> getAllCategories();
}