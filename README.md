# Spring-Boot---CRUD

<div style="display: flex;">
    <!-- SPRING BOOT LOGO -->
    <a href="https://spring.io/projects/spring-boot/">
            <img src="https://4.bp.blogspot.com/-ou-a_Aa1t7A/W6IhNc3Q0gI/AAAAAAAAD6Y/pwh44arKiuM_NBqB1H7Pz4-7QhUxAgZkACLcBGAs/s1600/spring-boot-logo.png" alt="SPRING BOOT LOGO" height="55" style="margin-right: 16px;" />
    </a>
    <!-- MYSQL LOGO -->
    <a href="https://www.mysql.com/">
        <img src="https://d1.awsstatic.com/asset-repository/products/amazon-rds/1024px-MySQL.ff87215b43fd7292af172e2a5d9b844217262571.png" alt="MYSQL LOGO" height="38"/>
    </a>
</div>

---

# SPRING BOOT ANNOTATIONS <!-- ANNOTATIONS -->

## RestController

The @RestController annotation in Spring is a special tag that marks a Java class as a web controller. It's like the traffic controller for your web application, deciding how to handle incoming requests from users or other systems.

```
@RestController
public class ProductController {

}
```

## Autowired

The @Autowired annotation is a powerful and widely used annotation in Spring Framework applications. It is used to automatically wire (inject) dependencies into a Spring-managed bean. When a class is marked with @Autowired, Spring will automatically identify the dependencies required by that class and provide the appropriate instances of those dependencies at runtime.

```
@Autowired
private ProductRepository productRepository;
```

## GetMapping

The @GetMapping annotation is another handy tag in Spring. It's like a signpost that tells the traffic controller (your @RestController) what to do when a user or another system sends a specific HTTP GET request to a particular URL.

```
@GetMapping("/product")
```

## PostMapping

The @PostMapping annotation in Spring is used to handle HTTP POST requests. When a client (user or another system) sends a POST request to your application, it typically indicates that they want to create or add a new resource on the server.

```
@PostMapping("/product")
```

## PutMapping

The @PutMapping annotation in Spring is used to handle HTTP PUT requests. When a client (user or another system) sends a PUT request to your application, it indicates that they want to update an existing resource on the server.

```
@PutMapping("/product/{id}")
```

## DeleteMapping

The @DeleteMapping annotation in Spring is used to handle HTTP DELETE requests. When a client (user or another system) sends a DELETE request to your application, it indicates that they want to delete a specific resource on the server.

```
@DeleteMapping("/product/{id}")
```

## RequestBody

The @RequestBody annotation in Spring is used to indicate that a parameter in a controller method should be bound to the request body of an incoming HTTP request. It is commonly used in conjunction with methods that handle POST, PUT, or PATCH requests, where the client sends data in the request body.

```
public Product saveProduct(@Valid @RequestBody Product product{

}
```

## PathVariable

The @PathVariable annotation in Spring is used to extract values from the URI (Uniform Resource Identifier) of an incoming HTTP request and bind them to method parameters in a Spring controller. It allows you to capture dynamic parts of the URL and use them as method arguments, making it easy to work with variables in your RESTful APIs.

```
public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product)
```

## Entity

The @Entity annotation in Java, specifically in the context of JPA (Java Persistence API), is used to mark a Java class as an entity, representing a table in a relational database. When you annotate a class with @Entity, you are telling the JPA framework that instances of this class should be persisted to the database and managed as database records.

```
@Entity
public class Product {

}
```

## Repository

The @Repository annotation is a special Spring annotation used in Java Spring Framework applications. It is used to indicate that a class is a repository, which is responsible for managing and interacting with data access operations, typically with a database.

When you annotate a class with @Repository, it serves as a data access object (DAO) that provides an abstraction layer between your business logic and the underlying data storage, such as a relational database. The repository class usually contains methods to perform CRUD (Create, Read, Update, Delete) operations and other data retrieval queries.

```
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
```

## Service

The @Service annotation is a special Spring annotation used in Java Spring Framework applications. It is used to indicate that a class is a service component, responsible for implementing business logic and providing services to other parts of the application.

When you annotate a class with @Service, it serves as a service layer that encapsulates the business logic of your application. This layer typically contains methods that perform specific tasks or operations, and it acts as an intermediary between the presentation layer (controllers) and the data access layer (repositories).

```
@Service
public class ProductServiceImpl implements ProductService {

}
```

## Useful Entity Class Annotations

@NotNull: Marks a field as not nullable.

@Size: Specifies the size constraints for a field (e.g., minimum and maximum length).

@Positive: Marks a numeric field with a positive value constraint.

@Negative: Marks a numeric field with a negative value constraint.

@PositiveOrZero: Marks a numeric field with a positive or zero value constraint.

@NegativeOrZero: Marks a numeric field with a negative or zero value constraint.

@NotBlank: Marks a String field with a constraint that the value must not be empty or whitespace.

@Email: Marks a String field as an email address.

@Pattern: Marks a String field with a regular expression pattern constraint.

## Useful Lombok Annotations

@Getter: Automatically generates getter methods for fields.

@Setter: Automatically generates setter methods for fields.

@NoArgsConstructor: Generates a no-argument constructor.

@RequiredArgsConstructor: Generates a constructor with required fields as parameters.

@AllArgsConstructor: Generates a constructor with all fields as parameters.

@Data: Combines @Getter, @Setter, @EqualsAndHashCode,

@ToString, and @RequiredArgsConstructor together.

@EqualsAndHashCode: Generates equals() and hashCode() methods based on the fields in the class.

@ToString: Generates a toString() method that prints the contents of the object.

@Builder: Provides a builder pattern for creating instances of the class.

# SPRING BOOT EXCEPTION ANNOTATIONS <!-- EXCEPTION HANDLING -->

## ControllerAdvice

The @ControllerAdvice annotation is a special Spring annotation used in Java Spring Framework applications. It is used to define a global exception handler that can handle exceptions thrown by any controller within the application.

## ResponseStatus

The @ResponseStatus annotation is a Spring annotation used in Java Spring Framework applications to customize the HTTP status code that is returned in the HTTP response when a particular controller method is invoked.

## ExceptionHandler

The @ExceptionHandler annotation is a Spring annotation used in Java Spring Framework applications to handle exceptions that may occur during the execution of controller methods. It allows you to define a method within a controller or a separate class to handle specific types of exceptions and provide a custom response when those exceptions occur.

# SPRING BOOT TESTING ANNOTATIONS <!-- TESTING -->

## WebMvcTest

The @WebMvcTest annotation is a specialized testing annotation provided by Spring Boot for testing Spring MVC controllers. It is used to test the behavior of MVC components in isolation, without starting the full application context. This makes the testing process faster and more focused on the web layer of your application.

```
@WebMvcTest(ProductController.class)
class ProductControllerTest {

}
```

## DataJpaTest

The @DataJpaTest annotation is part of Spring Boot and is used for integration testing of JPA (Java Persistence API) repositories. It provides a convenient way to test the JPA layer of your Spring application in isolation, without the need to set up the entire application context.

```
@DataJpaTest
class ProductRepositoryTest {

}
```

## SpringBootTest

The @SpringBootTest annotation is a powerful and versatile annotation in Spring Boot used for integration testing. It allows you to bootstrap the entire Spring application context, making it suitable for testing the application as a whole, including all the layers and components.

```
@SpringBootTest
class ProductServiceTest {

}
```

## MockBean

The @MockBean annotation is a powerful feature provided by the Spring Boot Test framework. It is used in conjunction with testing annotations like @SpringBootTest and @WebMvcTest to mock a Spring bean during the testing process. Mocking a bean allows you to replace the real implementation of a bean with a mock (a fake or simulated version) that you control, making it easier to isolate and test specific components in your application.

```
@MockBean
private ProductService productService;
```

## BeforeEach

The @BeforeEach annotation is used in JUnit 5 to indicate that a method should be executed before each test method in a test class. It allows you to set up the test environment and prepare the necessary data or resources before each individual test is executed. This ensures that each test starts with a clean and consistent state, making the tests independent and reliable.

## Test

The @Test annotation is a fundamental part of JUnit, used to mark a method as a test case that should be executed during testing. When using JUnit 4 or 5, this annotation allows the test runner to identify and execute the methods marked as tests. The method should not have any return type or parameters, and it should be public.

## SneakyThrows

The @SneakyThrows annotation is a feature provided by Project Lombok, which is a library that helps reduce boilerplate code in Java. This annotation is used to automatically convert checked exceptions into unchecked exceptions without the need for explicit try-catch blocks or throws declarations.

## DisplayName

The @DisplayName annotation is part of JUnit 5, a popular testing framework for Java. It is used to provide a custom display name for a test class or test method, making test reports and results more descriptive and user-friendly.

```
@DisplayName("Save Product Controller")
```

# PROJECT DEPENDENCIES <!-- PROJECT DEPENDENCIES -->

Spring Web

MySQL Driver

Spring Boot DevTools

Lombok

H2 Database

Spring Boot Actuator

# PROJECT BUILD <!-- PROJECT BUILD -->

## to Create JAR using Terminal

mvn clean install

## to Deployment First Navigate to target File

## to Run JAR File using Terminal

java -jar simple_crud-1.0.0.jar --spring.profiles.active=prod

## to check Health of the Application using Actuator

`http://localhost:8080/actuator`
