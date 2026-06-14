package com.jumpstart.food_ordering_system.repository;

import com.jumpstart.food_ordering_system.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// The repository layer is responsible for communicating with the database.
// By extending JpaRepository, we get built-in methods like findAll(), findById(), save() etc.
public interface CategoryRepository extends JpaRepository<Category, Long> {

}