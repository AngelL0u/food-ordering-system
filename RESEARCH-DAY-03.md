# Research Day 03 - Menu Feature (Data Layer + Service + Controller)

## Q1. What is JPA? What is Hibernate? How are they related?
JPA (Java Persistence API) is a specification that defines how Java objects should be mapped to database tables. Hibernate is an implementation of JPA. JPA defines the rules and Hibernate does the actual work. You write JPA annotations like @Entity and @ManyToOne, and Hibernate translates them into SQL queries.

## Q2. What is the difference between @Entity and @Table?
@Entity tells Spring that this Java class represents a database table. @Table is optional and lets you specify the exact table name. If you leave out @Table, JPA uses the class name as the table name by default.

## Q3. What is a foreign key? What is @ManyToOne? Give 2 real-world examples.
A foreign key is a column in one table that references the primary key of another table, creating a link between them. @ManyToOne means many records in this table belong to one record in another table.
- Example 1: Many menu items belong to one category.
- Example 2: Many orders belong to one customer.

## Q4. What does @JoinColumn(name = "category_id") do?
It tells JPA to create a column called category_id in the menus table that stores the foreign key referencing the category table's primary key.

## Q5. Why store price as BigDecimal and not double?
double uses binary floating point which cannot represent all decimal numbers exactly. For example, 0.1 + 0.2 in double gives 0.30000000000000004. BigDecimal stores numbers exactly and is essential for money where rounding errors could cause financial mistakes.

## Q6. What does FetchType LAZY vs EAGER mean? What is the default for @ManyToOne?
EAGER means the related entity is loaded from the database immediately when the parent is loaded. LAZY means the related entity is only loaded when you actually access it. The default for @ManyToOne is EAGER.

## Q7. What is the N+1 query problem?
The N+1 problem happens when you load a list of N entities and then for each one, a separate query is fired to load a related entity. For example, loading 10 menus and then firing 10 more queries to load each category results in 11 total queries instead of 1. It causes serious performance issues.

## Q8. What is dependency injection? Constructor injection vs field injection — which is preferred and why?
Dependency injection is when Spring automatically provides the objects a class needs instead of the class creating them itself. Constructor injection passes dependencies through the constructor. Field injection uses @Autowired on a field directly. Constructor injection is preferred because it makes dependencies explicit, supports immutability with final fields, and makes the class easier to test.

## Q9. What does @RequiredArgsConstructor (Lombok) do?
It generates a constructor that includes all fields marked as final or @NonNull. This is the Lombok way to enable constructor injection without writing the constructor manually.

## Q10. What is the role of the SERVICE layer? Why must it be separate from the controller?
The service layer contains business logic. The controller only handles HTTP requests and responses. Keeping them separate means the business logic can be reused, tested independently, and changed without affecting the API layer.

## Q11. Why MUST you validate that categoryId exists before saving a menu?
If you save a menu with a categoryId that does not exist in the database, the foreign key constraint will throw a database error. Validating first lets you return a meaningful 404 response to the client instead of a confusing 500 error.

## Q12. Difference between save() and saveAndFlush()?
save() saves the entity and the change may be kept in memory until the transaction ends. saveAndFlush() saves and immediately writes to the database. For most cases save() is sufficient.

## Q13. Why write private mapper methods (entity <-> dto)?
Mapper methods keep the conversion logic in one place. If the entity or DTO changes, you only update the mapper instead of changing every method. It makes the code cleaner and easier to maintain.

---

## Self-Quiz

### Q1. Why didn't we add @OneToMany on Category for menus?
We are using a unidirectional relationship. Category does not need to know about menus. Adding @OneToMany on both sides creates a bidirectional relationship which adds complexity and can cause performance issues if not managed carefully.

### Q2. What would ddl-auto = create-drop do? When would you use it?
It creates the tables when the app starts and drops them when it stops. You would use it in testing so you always start with a clean database.

### Q3. If you delete a Category that has menus, what happens by default?
The database will throw a foreign key constraint violation error because menus still reference that category. You would need to delete or reassign the menus first.

### Q4. Why is BigDecimal better than double for storing money values?
BigDecimal is exact. double uses binary floating point which cannot accurately represent many decimal fractions, leading to rounding errors that are unacceptable when dealing with money.