# Research Day 01 - CRUD & REST API Concepts

## Q1. What does CRUD stand for?
CRUD stands for Create, Read, Update and Delete. These are the four basic operations you can perform on data in a database or application.

## Q2. Difference between HTTP methods POST, PUT, PATCH, DELETE?
- **POST** - Used to create a new resource. The data is sent in the request body.
- **PUT** - Used to update an existing resource completely. You replace the entire resource with new data.
- **PATCH** - Used to partially update a resource. You only send the fields you want to change.
- **DELETE** - Used to remove a resource from the server.

## Q3. HTTP Status Codes
- a. A new category was created → **201 Created**
- b. A category was deleted successfully → **204 No Content**
- c. The id requested does not exist → **404 Not Found**
- d. The request body is missing a required field → **400 Bad Request**
- e. The user is logged in but not allowed → **403 Forbidden**

## Q4. @RequestBody, @RequestParam, @PathVariable

- **@RequestBody** - Reads data from the request body (used for POST/PUT):
```java
public CategoryDto addCategory(@RequestBody CategoryDto dto)
```

- **@PathVariable** - Reads a value from the URL path:
```java
public CategoryDto getById(@PathVariable Long id)  // URL: /api/categories/1
```

- **@RequestParam** - Reads a query parameter from the URL:
```java
public List<CategoryDto> search(@RequestParam String name)  // URL: /api/categories?name=Pizza
```

## Q5. Jakarta Bean Validation
Jakarta Bean Validation is a framework that allows you to add rules to your fields to ensure data is valid before it is processed.
- **@Valid** - Tells Spring to validate the object before using it.
- **@NotBlank** - Ensures the field is not null, empty, or just whitespace.
- **@Size** - Ensures the field length is within a specified range.

## Q6. Why return a DTO and not the entity?
1. **Security** - The entity may contain sensitive fields (like passwords) that you don't want to expose in the API response.
2. **Flexibility** - The DTO can be shaped differently from the entity, allowing you to return only the fields the client needs without changing the database structure.

## Q7. What is Optional<T>?
Optional<T> is a container object that may or may not contain a value. findById returns Optional because the record might not exist in the database. Instead of returning null (which can cause NullPointerExceptions), Optional forces you to handle the case where the value is missing.

---

## Self-Quiz

### Q1. Why ResponseEntity instead of returning the object?
ResponseEntity gives you full control over the HTTP response, including the status code, headers, and body. Returning just an object always gives status 200, but with ResponseEntity you can return 201 for created or 204 for deleted.

### Q2. What status should a successful DELETE return? Why?
204 No Content, because the resource has been deleted and there is nothing to return in the response body.

### Q3. Update only one field - PUT or PATCH?
PATCH, because PUT replaces the entire resource. If you only want to update one field, using PUT would require sending all other fields too, which is wasteful and error-prone.

### Q4. What happens if you forget @Valid on the controller?
The validation annotations on the DTO are ignored and any input, including empty or invalid data, will be accepted and processed.

### Q5. Why must update/delete have {id} in the URL but create does not?
Update and delete need to target a specific existing resource, so the id is required to identify which one. Create generates a new resource so there is no existing id yet.