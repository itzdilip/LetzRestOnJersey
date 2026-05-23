# Step-by-Step Modernization Guide

This document tracks the enhancements made to the **LetzRestOnJersey** project to bring it up to modern (2024-2026) software engineering standards.

## Phase 1: Spring Boot Migration
The goal is to move away from a traditional `.war` deployment with complex XML configurations to a self-contained, executable Spring Boot application.

### Step 1: Update Build Configuration (`pom.xml`)
- Added `spring-boot-starter-parent` as the project parent for version management.
- Added `spring-boot-starter-web` to replace manual Jersey/Spring setups.
- Removed legacy `jersey-client`, `jersey-spring`, and redundant JAX-RS dependencies.
- Updated to Java 8+ (using Spring Boot defaults).

### Step 2: Create Main Application Class
- Created `com.smd.Application` to serve as the entry point for the application.
- Used `@SpringBootApplication` to enable auto-configuration, component scanning, and property support.

### Step 3: Remove Legacy XML Configuration
- Deleted `src/main/webapp/WEB-INF/web.xml`.
- Deleted `src/main/resources/applicationContext.xml`.
- Deleted `src/test/resources/applicationContext.xml`.

---
## Phase 2: Refactoring to Spring Boot with Jersey
Now that the project is a Spring Boot application, we integrate Jersey (JAX-RS) as the primary REST framework.

### Step 4: Configure Jersey in Spring Boot
- Created `JerseyConfig` class extending `ResourceConfig`.
- Registered `ServersResource` and `JodiCloudExceptionMapper` with Jersey.
- This allows us to use JAX-RS annotations while benefiting from Spring's dependency injection.

### Step 5: Implement JAX-RS Exception Mapping
- Restored and modernized `JodiCloudExceptionMapper` as a `@Provider`.
- This provides a standard JAX-RS way to handle exceptions globally.

---
## Phase 3: Persistence Layer
In this phase, we replace the volatile in-memory `HashMap` with a persistent database using Spring Data JPA and H2.

### Step 6: Configure JPA and H2
- Added `spring-boot-starter-data-jpa` and `h2` dependencies to `pom.xml`.
- JPA provides an abstraction over SQL, allowing us to work with Java objects (Entities) instead of raw queries.
- H2 is an in-memory database used for development and testing.
### Step 7: Refactor `Server` Model to an Entity
- Added `@Entity` and `@Table` annotations to the `Server` class.
- Marked `serverID` as the primary key using `@Id`.
- Added `@XmlRootElement` and `@Transient` for JAXB compatibility alongside JPA, ensuring Jersey can still serialize the model correctly.

---

## Phase 7: Proposed Jersey-Centric Enhancements
The following enhancements are recommended to further showcase the power and flexibility of Jersey as the core REST framework.

### 1. Declarative Linking (Jersey HATEOAS)
- **Goal:** Replace manual link building with Jersey's declarative linking feature.
- **Action:** Add `jersey-declarative-linking` dependency and use annotations like `@InjectLink` directly on model fields to automatically generate HATEOAS links based on resource methods.

### 2. Custom Jersey Filters & Interceptors
- **Goal:** Implement cross-cutting concerns (logging, security, performance tracking) using JAX-RS standard filters.
- **Action:** Create classes implementing `ContainerRequestFilter` and `ContainerResponseFilter` to intercept and process requests/responses globally.

### 3. Advanced Media Type Support (Content Negotiation)
- **Goal:** Better support for diverse media types beyond JSON and XML.
- **Action:** Implement custom `MessageBodyReader` and `MessageBodyWriter` for specialized formats (e.g., CSV or custom binary protocols).

### 4. Asynchronous Request Processing
- **Goal:** Improve scalability by handling long-running requests asynchronously.
- **Action:** Use Jersey's `@Suspended` annotation and `AsyncResponse` to free up worker threads while waiting for background tasks (like external API calls in `ProxyHelper`) to complete.

### 5. Jersey Managed Client with Proxy Support
- **Goal:** Standardize external calls using the Jersey Client API instead of `RestTemplate`.
- **Action:** Configure a managed `Client` instance in `JerseyConfig` that includes proxy settings, and inject it into `ProxyHelper` for a purely JAX-RS based client solution.

### Step 8: Create `ServerRepository`
- Introduced `ServerRepository` interface extending `JpaRepository`.
- This interface automatically provides standard CRUD (Create, Read, Update, Delete) operations without needing boilerplate code.

### Step 9: Update `ServiceManager` to use Repository
- Refactored `ServiceMangerImpl` to inject `ServerRepository`.
- Replaced the `HashMap` logic with repository calls (`save`, `findAll`, `findById`).

---
## Phase 4: API Documentation & Modern HATEOAS
We add interactive documentation and implement standard HATEOAS (Hypermedia as the Engine of Application State) to make the API self-discoverable.

### Step 10: Integrate SpringDoc OpenAPI (Swagger)
- Added `springdoc-openapi-ui` to `pom.xml`.
- This automatically generates a Swagger UI at `/swagger-ui.html`, allowing developers to test the API directly from the browser.

### Step 11: Implement HATEOAS in Jersey
- Restored the custom `ResponseLinks` annotation and reflection-based link building logic.
- Used JAX-RS `UriInfo` to build dynamic, type-safe links (e.g., a "self" link for each server).
- This maintains the original project's HATEOAS approach while running within Spring Boot.

---
## Phase 5: Validation & Testing Update
We ensure data integrity by adding request validation and modernize our testing suite.

### Step 12: Add Request Validation
- Added `spring-boot-starter-validation` to `pom.xml`.
- Annotated `ServerRequest` with Bean Validation constraints (`@NotBlank`, `@Size`).
- Enabled validation in `ServersResource` using the `@Valid` annotation on request bodies.
- This prevents invalid data (e.g., empty server names) from reaching the service layer.

### Step 13: Modernize Tests (Ongoing)
- Updated `pom.xml` to use `spring-boot-starter-test`, which includes JUnit 5 and AssertJ.
- (Note: Existing JUnit 4 tests can run via the `junit-vintage-engine`, but migration to JUnit 5 is recommended for new tests).

---
## Phase 6: Modernizing External Calls
The original project used legacy Jersey 1.x clients for external calls. We modernize this by using Spring's `RestTemplate`.

### Step 14: Refactor `ProxyHelper` to use `RestTemplate`
- Replaced `com.sun.jersey.api.client` with `org.springframework.web.client.RestTemplate`.
- `RestTemplate` is the standard Spring way to make synchronous HTTP requests, providing better integration and simpler configuration.




