package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.MenuDto;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.entity.Menu;
import com.jumpstart.food_ordering_system.exception.CategoryNotFoundException;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import com.jumpstart.food_ordering_system.repository.MenuRepository;
import com.jumpstart.food_ordering_system.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Response<MenuDto> createMenu(MenuDto dto) {

        // Validate that the category exists
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));

        // Map DTO to entity
        Menu menu = mapToEntity(dto, category);

        // Save to database
        Menu saved = menuRepository.save(menu);

        return Response.success("Menu created", mapToDto(saved));
    }

    @Override
    public Response<List<MenuDto>> getAllMenus() {

        List<MenuDto> menus = menuRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();

        return Response.success("Menus retrieved", menus);
    }

    @Override
    public Response<MenuDto> getMenuById(Long id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Menu not found with id: " + id));

        return Response.success("Menu retrieved", mapToDto(menu));
    }

    // Private helper — converts Menu entity to MenuDto
    private MenuDto mapToDto(Menu menu) {
        MenuDto dto = new MenuDto();
        dto.setId(menu.getId());
        dto.setName(menu.getName());
        dto.setDescription(menu.getDescription());
        dto.setPrice(menu.getPrice());
        dto.setImageUrl(menu.getImageUrl());
        dto.setCategoryId(menu.getCategory().getId());
        dto.setCategoryName(menu.getCategory().getName());
        return dto;
    }

    // Private helper — converts MenuDto to Menu entity
    private Menu mapToEntity(MenuDto dto, Category category) {
        return Menu.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .category(category)
                .build();
    }
}