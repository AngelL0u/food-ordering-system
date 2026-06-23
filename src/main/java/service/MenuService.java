package com.jumpstart.food_ordering_system.service;

import com.jumpstart.food_ordering_system.dto.MenuDto;
import com.jumpstart.food_ordering_system.response.Response;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuService {

    Response<MenuDto> createMenu(MenuDto dto);

    Response<?> getAllMenus(Long categoryId, String search, Pageable pageable);

    Response<MenuDto> getMenuById(Long id);

    Response<MenuDto> updateMenu(Long id, MenuDto dto);

    Response<Void> deleteMenu(Long id);
}