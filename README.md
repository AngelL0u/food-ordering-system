# Food Ordering System

A Spring Boot project for a food ordering system built as part of the JumpStart training programme.

## Part 4: Research Questions

### 1. What is Spring Boot?
Spring Boot is a framework built on top of Spring that makes it easy to create standalone, production-ready Java applications. It removes the need for complex configuration by providing sensible defaults and auto-configuration, allowing developers to focus on writing business logic rather than setup.

### 2. What is Maven?
Maven is a build automation and project management tool used in Java projects. It handles downloading dependencies, compiling code, running tests, and packaging the application into a deployable format.

### 3. What is the purpose of pom.xml?
The pom.xml (Project Object Model) is the configuration file for Maven. It defines the project's dependencies, plugins, build settings, and metadata such as the group ID, artifact ID, and version.

### 4. What is the purpose of application.properties?
application.properties is the configuration file for a Spring Boot application. It is used to set properties such as the database connection URL, username, password, server port, and other application settings.

### 5. What does @SpringBootApplication do?
@SpringBootApplication is an annotation that combines three annotations: @Configuration, @EnableAutoConfiguration, and @ComponentScan. It marks the main class of a Spring Boot application and enables auto-configuration and component scanning.

### 6. Why do developers use dependency management tools such as Maven?
Dependency management tools like Maven save developers from manually downloading and managing JAR files. They automatically resolve dependencies, handle version conflicts, and ensure that all team members use the same library versions.

### 7. What is a REST API?
A REST API (Representational State Transfer Application Programming Interface) is a way for applications to communicate over HTTP using standard methods such as GET, POST, PUT, and DELETE. It allows different systems to exchange data in a structured format, typically JSON.

### 8. What is JSON?
JSON (JavaScript Object Notation) is a lightweight data format used to store and exchange data. It is easy for humans to read and write, and easy for machines to parse. It uses key-value pairs and is commonly used in REST APIs.

### 9. What is Dependency Injection?
Dependency Injection is a design pattern where an object receives its dependencies from an external source rather than creating them itself. In Spring Boot, the framework manages and injects dependencies automatically, making code more modular and easier to test.

## Part 5: Package Structure

| Package | Purpose |
|---------|---------|
| controller | Handles incoming HTTP requests and returns responses |
| service | Contains business logic and processes data |
| repository | Manages database operations using Spring Data JPA |
| entity | Defines database table structures as Java classes |
| dto | Data Transfer Objects used to pass data between layers |
| config | Holds application configuration classes |
| exception | Contains custom exception classes and error handling |

## Screenshots

Screenshots of the development environment setup are available in the `screenshots` folder.

## Endpoints

| Method | URL                       | Body         |
|--------|---------------------------|--------------|
| POST   | /api/categories           | { "name" }   |
| GET    | /api/categories           | -            |
| GET    | /api/categories/{id}      | -            |
| PUT    | /api/categories/{id}      | { "name" }   |
| DELETE | /api/categories/{id}      | -            |

## API Response Format

Every endpoint returns a consistent JSON structure:

```json
{
    "statusCode": 200,
    "message": "Category retrieved",
    "data": {
        "id": 1,
        "name": "Fast Food"
    },
    "timestamp": "2026-06-18T08:42:11"
}
```

On error, the `data` field is omitted.