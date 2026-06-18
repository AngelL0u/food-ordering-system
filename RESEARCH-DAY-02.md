# Research Day 02 - Standard Response Wrapper

## Q1. What is a Java generic type? Why is <T> useful?
A generic type is a class or method that works with any data type specified at compile time using a type parameter like <T>. It is useful because it allows you to write one class that works for many types without duplicating code. For example, Response<T> can wrap a CategoryDto, a List, or any other object without writing a separate Response class for each.

## Q2. What does Lombok @Builder generate behind the scenes?
@Builder generates a static inner Builder class with a method for each field. Instead of calling a constructor with many parameters, you chain methods like Response.builder().statusCode(200).message("OK").data(dto).build(). It makes object creation readable and flexible.

## Q3. What is the Builder design pattern? When to use it?
The Builder pattern separates the construction of a complex object from its representation. Use it when an object has many optional fields, when constructors would become hard to read with too many parameters, or when you want to create immutable objects step by step.

## Q4. What is LocalDateTime? How is it different from Date?
LocalDateTime is a modern Java class from the java.time package that represents date and time without a timezone. Date is an older class that represents a moment in time including timezone offset and is harder to work with. LocalDateTime is immutable, thread-safe, and has a cleaner API.

## Q5. Why does a consistent response format matter to frontend developers?
A consistent format means the frontend always knows where to find the data, the status code, and the message. Without it, every endpoint could return a different structure and the frontend would need custom handling for each one. Consistency reduces bugs and speeds up integration.

## Q6. What does @JsonInclude(JsonInclude.Include.NON_NULL) do?
It tells Jackson not to include fields that are null when serialising the object to JSON. For example, if the data field is null on an error response, it will not appear in the JSON output at all, keeping the response clean.

## Q7. What is a static factory method? Why use Response.success(...) instead of new Response<>()?
A static factory method is a static method that creates and returns an instance of the class. Response.success() is cleaner because it sets all required fields in one call, gives the method a meaningful name that describes its intent, and hides the builder complexity from the caller.

---

## Self-Quiz

### Q1. Why use generic <T> instead of Object for data field?
Using Object would require casting when reading the data, which is error-prone and loses type safety. Generic <T> preserves the type information at compile time so the compiler can catch mistakes early.

### Q2. Difference between Response<T> and ResponseEntity<T>? Can you have both at once?
ResponseEntity controls the HTTP response including status code and headers. Response<T> is our custom body with a consistent JSON structure. Yes, you can have both — ResponseEntity<Response<CategoryDto>> means the HTTP status is set by ResponseEntity and the body contains our Response wrapper.

### Q3. If a request fails, what statusCode does Response hold?
The statusCode inside our Response object holds the error code such as 404 or 400. This is separate from the HTTP status code set by ResponseEntity.

### Q4. Why add a timestamp?
A timestamp tells the client exactly when the response was generated. It is useful for debugging, logging, and caching so the client knows how fresh the data is.