package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.MenuDto;
import com.jumpstart.food_ordering_system.entity.Category;
import com.jumpstart.food_ordering_system.entity.Menu;
import com.jumpstart.food_ordering_system.exception.CategoryNotFoundException;
import com.jumpstart.food_ordering_system.repository.CategoryRepository;
import com.jumpstart.food_ordering_system.repository.MenuRepository;
import com.jumpstart.food_ordering_system.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Response<MenuDto> createMenu(MenuDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));
        Menu saved = menuRepository.save(mapToEntity(dto, category));
        return Response.success("Menu created", mapToDto(saved));
    }

    @Override
    public Response<?> getAllMenus(Long categoryId, String search, Pageable pageable) {
        Page<Menu> page = menuRepository.findWithFilters(categoryId, search, pageable);

        List<MenuDto> items = page.getContent()
                .stream()
                .map(this::mapToDto)
                .toList();

        // Build page metadata
        Map<String, Object> result = new HashMap<>();
        result.put("items", items);
        result.put("totalElements", page.getTotalElements());
        result.put("totalPages", page.getTotalPages());
        result.put("number", page.getNumber());
        result.put("size", page.getSize());
        result.put("first", page.isFirst());
        result.put("last", page.isLast());

        return Response.success("Menus retrieved", result);
    }

    @Override
    public Response<MenuDto> getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Menu not found with id: " + id));
        return Response.success("Menu retrieved", mapToDto(menu));
    }

    @Override
    public Response<MenuDto> updateMenu(Long id, MenuDto dto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Menu not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));

        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());
        menu.setCategory(category);

        Menu updated = menuRepository.save(menu);
        return Response.success("Menu updated", mapToDto(updated));
    }

    @Override
    public Response<Void> deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Menu not found with id: " + id));
        menuRepository.delete(menu);
        return Response.success("Menu deleted", null);
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