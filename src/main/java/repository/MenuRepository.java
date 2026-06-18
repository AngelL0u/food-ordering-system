package com.jumpstart.food_ordering_system.repository;

import com.jumpstart.food_ordering_system.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

// Provides built-in methods like findAll(), findById(), save(), delete()
public interface MenuRepository extends JpaRepository<Menu, Long> {

}