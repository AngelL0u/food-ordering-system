package com.jumpstart.food_ordering_system.exception;

// Custom exceptions allow us to handle specific error scenarios in a meaningful way.
// Instead of throwing a generic exception, we throw one that clearly describes the problem.
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
}