package com.jumpstart.food_ordering_system.entity;

import jakarta.persistence.*;

// This class represents the 'category' table in the database.
// Each instance of this class corresponds to one row in the table.
@Entity
@Table(name = "category")
public class Category {

    // This field is the primary key, auto-incremented by the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}