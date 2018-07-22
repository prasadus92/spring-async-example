# spring-boot-async-example

Fetching data from 2 REST endpoints asynchronously and merging the responses.

## Task Description

This Application gathers data from two REST endpoints asynchronously, merges the responses and displays them as a JSON.
As an example, you could use these two endpoints:
* http://jsonplaceholder.typicode.com/users/1 (to obtain a user's data)
* http://jsonplaceholder.typicode.com/posts?userId=1 (to obtain all comments written by that user)

## Development

This project is built using the following tools:

1. JDK 1.8
2. [Spring Boot 2](https://spring.io/projects/spring-boot)
3. [Spring Async](https://spring.io/guides/gs/async-method/)
3. [Maven](https://maven.apache.org/)
4. [Swagger](https://swagger.io/) (for API documentation; using SpringFox)

## Solution

The logic of the solution is very straightforward, the Application uses Spring's `@EnableAsync` to configure a `ThreadPoolTaskExecutor` which will be used to run async methods.
In this application, fetching user and his data configured to run asynchronously using this `ThreadPoolTaskExecutor` provided. Spring takes care of managing this executor.

## Building and running the Application

As Maven is used as the build system, run:

    ./mvnw clean install

To run the project, just run one of the following commands:

    java -jar target/data-1.0-SNAPSHOT.jar
    
    ./mvnw spring-boot:run
    
Navigate to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to see the available endpoints.
    
## Testing

To run the tests, use:

    ./mvnw clean test
    
## ToDo

* Make the ThreadPoolExecutor in AsyncConfig configurable from Spring Application properties or from environment variables.
* Improve the test coverage.
* Integration Tests using `SpringBootTest` are expensive as it results in loading the entire Application Context, add more tests using `WebMvcTest`.
* Initially tried with Spring WebFlux (with Reactor Core), but it seemed like an overkill for this task. But if the requirements are changed to have User's posts as a stream, better to implement this using WebFlux.