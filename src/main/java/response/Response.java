package com.jumpstart.food_ordering_system.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// A generic wrapper for all API responses.
// <T> means this class can wrap any type of data.
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private int statusCode;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    // Factory method for successful responses
    public static <T> Response<T> success(String message, T data) {
        return Response.<T>builder()
                .statusCode(200)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Factory method for error responses
    public static <T> Response<T> error(int code, String message) {
        return Response.<T>builder()
                .statusCode(code)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}