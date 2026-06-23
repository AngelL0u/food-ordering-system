# Food Ordering System

A Spring Boot REST API for a food ordering system built as part of the JumpStart training programme.

## Part 4: Research Questions

### 1. What is Spring Boot?
Spring Boot is a framework built on top of Spring that makes it easy to create standalone, production-ready Java applications with minimal configuration.

### 2. What is Maven?
Maven is a build automation tool used in Java projects. It handles downloading dependencies, compiling code, and packaging the application.

### 3. What is the purpose of pom.xml?
The pom.xml defines the project's dependencies, plugins, build settings, and metadata.

### 4. What is the purpose of application.properties?
application.properties configures the Spring Boot application including database connection, server port, and JPA settings.

### 5. What does @SpringBootApplication do?
It combines @Configuration, @EnableAutoConfiguration, and @ComponentScan to bootstrap the application.

### 6. Why do developers use dependency management tools such as Maven?
They automatically resolve dependencies, handle version conflicts, and ensure consistency across team members.

### 7. What is a REST API?
A REST API allows applications to communicate over HTTP using standard methods like GET, POST, PUT, and DELETE.

### 8. What is JSON?
JSON is a lightweight data format using key-value pairs, commonly used in REST APIs.

### 9. What is Dependency Injection?
Spring automatically provides objects a class needs instead of the class creating them itself.

## API Response Format

Every endpoint returns a consistent JSON structure:

```json
{
    "statusCode": 200,
    "message": "Menu retrieved",
    "data": {
        "id": 1,
        "name": "Cheese Burger",
        "description": "Beef patty with cheese",
        "price": 49.99,
        "imageUrl": "https://placehold.co/300",
        "categoryId": 1,
        "categoryName": "Fast Food"
    },
    "timestamp": "2026-06-23T10:30:00"
}
```

On error, the `data` field is omitted.

## Endpoints

### Category Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | /api/categories | Get all categories |
| GET | /api/categories/{id} | Get category by id |
| POST | /api/categories | Create a category |
| PUT | /api/categories/{id} | Update a category |
| DELETE | /api/categories/{id} | Delete a category (409 if it has menus) |

### Menu Endpoints

| Method | Path | Description | Query Params |
|--------|------|-------------|--------------|
| POST | /api/menu | Create a menu item | - |
| GET | /api/menu | List menus | categoryId, search, page, size, sort |
| GET | /api/menu/{id} | Get menu by id | - |
| PUT | /api/menu/{id} | Update a menu item | - |
| DELETE | /api/menu/{id} | Delete a menu item | - |

### Example GET with all query params

### Example paginated response

```json
{
    "statusCode": 200,
    "message": "Menus retrieved",
    "data": {
        "items": [...],
        "totalElements": 28,
        "totalPages": 3,
        "number": 0,
        "size": 10,
        "first": true,
        "last": false
    },
    "timestamp": "2026-06-23T10:30:00"
}
```

## Package Structure

| Package | Purpose |
|---------|---------|
| controller | Handles incoming HTTP requests |
| service | Contains business logic |
| repository | Manages database operations |
| entity | Defines database table structures |
| dto | Data Transfer Objects |
| config | Application configuration |
| exception | Custom exceptions and error handling |
| response | Generic Response wrapper |

## Screenshots

Screenshots are available in the `screenshots` folder organised by day.