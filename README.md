# LetzRestOnJersey (Modernized)

This project demonstrates how to build and modernize RESTful services using **Jersey (JAX-RS)** integrated with **Spring Boot**. It serves as a comprehensive example of maintaining a Jersey-centric architecture while leveraging modern cloud-native standards.

## Key Features
- **Jersey & Spring Boot Integration:** Uses `spring-boot-starter-jersey` for a seamless JAX-RS experience within a Spring Boot container.
- **Persistence with Spring Data JPA:** Replaced in-memory storage with an H2 database and JPA entities.
- **Modern HATEOAS:** Demonstrates dynamic link building using JAX-RS `UriInfo` and custom annotations.
- **Global Exception Mapping:** Uses `@Provider` and `ExceptionMapper` for standardized error responses.
- **API Documentation:** Integrated **SpringDoc OpenAPI (Swagger)** for interactive API exploration.
- **Validation:** Implements Bean Validation (JSR-303) for request payloads.
- **Modern Client Calls:** Uses Spring's `RestTemplate` for handling external HTTP calls through proxies.

## Technology Stack
- **Framework:** Jersey 2.x (via Spring Boot 2.7.x)
- **Language:** Java 8+
- **Database:** H2 (In-memory)
- **ORM:** Spring Data JPA
- **Build Tool:** Maven
- **Documentation:** OpenAPI 3 / Swagger UI

## Getting Started
### Prerequisites
- JDK 1.8 or higher
- Maven 3.6+

### Documentation
*   [ARCHITECTURE.md](ARCHITECTURE.md) - Deep dive into design patterns, architectural flow, and developer guides.
*   [ENHANCEMENT_STEPS.md](ENHANCEMENT_STEPS.md) - History and roadmap of the modernization effort.

### Running the Application
```bash
# Using Maven
mvn spring-boot:run

# Using Docker
docker build -t letzrest-jersey .
docker run -p 8080:8080 letzrest-jersey
```
The application will start on port `8080`.

### API Documentation
Once running, you can access the Swagger UI at:
`http://localhost:8080/swagger-ui.html`

### Testing
```bash
mvn test
```

## Modernization Journey
For a detailed step-by-step guide on how this project was migrated from a legacy Jersey `.war` setup to its current state, see [ENHANCEMENT_STEPS.md](ENHANCEMENT_STEPS.md).
