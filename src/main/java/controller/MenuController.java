package com.jumpstart.food_ordering_system.controller;

import com.jumpstart.food_ordering_system.dto.MenuDto;
import com.jumpstart.food_ordering_system.response.Response;
import com.jumpstart.food_ordering_system.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    // POST /api/menu - create a new menu item
    @PostMapping
    public ResponseEntity<Response<MenuDto>> create(@RequestBody @Valid MenuDto dto) {
        return ResponseEntity.ok(menuService.createMenu(dto));
    }

    // GET /api/menu - get all menu items
    @GetMapping
    public ResponseEntity<Response<List<MenuDto>>> all() {
        return ResponseEntity.ok(menuService.getAllMenus());
    }

    // GET /api/menu/{id} - get one menu item by id
    @GetMapping("/{id}")
    public ResponseEntity<Response<MenuDto>> byId(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }
}