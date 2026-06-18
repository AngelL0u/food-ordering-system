package com.jumpstart.food_ordering_system.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class MenuDto {

    private Long id;

    @NotBlank(message = "Menu name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price must be a positive number")
    private BigDecimal price;

    private String imageUrl;

    // Used as input when creating a menu
    @NotNull(message = "Category ID is required")
    private Long categoryId;

    // Used as output only — shows the category name in the response
    private String categoryName;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}