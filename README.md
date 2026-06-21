# Brewery Management API

**Version 1.2.0 - Functional MVP**

RESTful API built with Spring Boot for managing brewery recipes and ingredients.

## Overview

This project demonstrates backend development concepts such as layered architecture, REST API design, JPA entity relationships, validation, exception handling, and relational database management.

The API allows managing ingredients, recipes, and the relationships between them through a structured Spring Boot architecture.

---

## Features

### Ingredient Management

- Create ingredients
- Retrieve all ingredients
- Retrieve ingredients by ID
- Update ingredients
- Delete ingredients
- Input validation
- Automatic name normalization
- Prevent duplicate ingredient names
- Database integrity validation

### Recipe Management

- Create recipes
- Retrieve all recipes
- Retrieve recipes by ID
- Update recipes
- Delete recipes
- Associate ingredients with recipes
- Manage Many-to-Many relationships

### API Features

- Layered architecture
- Global exception handling
- RESTful endpoint design
- PostgreSQL relational database
- Spring Data JPA persistence
- Hibernate ORM
- Request validation
- Database constraints
- Swagger/OpenAPI documentation
- Postman API testing collection

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- Bean Validation
- Swagger/OpenAPI
- Postman

---

## Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL Database
```

### Project Structure

```text
controller  -> REST endpoints
service     -> Business logic
repository  -> Data access layer
model       -> JPA entities
exception   -> Custom exceptions and global exception handling
```
---

### API Documentation

The API includes Swagger/OpenAPI documentation.

Once the application is running, access:

```text
http://localhost:8080/swagger-ui/index.html
```
Swagger allows testing and exploring all available endpoints directly from the browser.

---

### API Testing

The repository includes a Postman collection with predefined requests for testing the API.

Included operations:

- Ingredient CRUD operations
- Recipe CRUD operations
- Recipe–Ingredient relationship management
- Validation and error handling scenarios

Import the collection:

```text
postman/Brewery-Management-API.postman_collection.json
```
Run the application:
```bash
mvn spring-boot:run
```
The API will be available at:

```text
http://localhost:8080
```

---

## API Endpoints

### Ingredients

| Method | Endpoint |
|----------|----------|
| GET | /api/ingredients |
| GET | /api/ingredients/{id} |
| POST | /api/ingredients |
| PUT | /api/ingredients/{id} |
| DELETE | /api/ingredients/{id} |

### Recipes

| Method | Endpoint |
|----------|----------|
| GET | /api/recipes |
| GET | /api/recipes/{id} |
| POST | /api/recipes |
| PUT | /api/recipes/{id} |
| DELETE | /api/recipes/{id} |
| POST | /api/recipes/{recipeId}/ingredients/{ingredientId} |

---

## Running Locally

Clone the repository:

```bash
git clone https://github.com/fran9300/brewery-management-api.git
```

Navigate to the project folder:

```bash
cd brewery-management-api
```

Run the application:

```bash
mvn spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

---

## Project Status

Functional MVP

The core API is fully functional and supports ingredient and recipe management, including entity relationships and exception handling.

Future improvements are planned and listed in the roadmap section.

---

## Database Model

![ER Diagram](images/er-diagram.png)

The application uses a Many-to-Many relationship between recipes and ingredients through the `recipe_ingredients` join table.

Relationship:

```text
Recipe
    ↔ Many-to-Many ↔
Ingredient
```

---

### Project Status

Functional MVP

The core API is fully functional and includes:

- Ingredient management
- Recipe management
- Entity relationships
- PostgreSQL persistence
- Validation
- Exception handling
- API documentation
- API testing collection


---

## Roadmap

Future improvements:

- [ ] DTO implementation
- [ ] Unit testing
- [ ] Docker and Docker Compose support
- [ ] Authentication and authorization
- [ ] Flyway database migrations
- [ ] CI/CD pipeline with GitHub Actions
- [ ] SonarQube code quality analysis
- [ ] Automated code formatting with Maven plugins

---

## Learning Goals

This project focuses on practicing:

- REST API design
- Spring Boot architecture
- Dependency injection
- Entity relationships with JPA
- Exception handling
- Database persistence
- API development best practices
- Backend development best practices

## Release Notes

### v1.2.0

Improvements:

- Added Swagger/OpenAPI documentation
- Added Postman collection for API testing
- Improved API testing workflow
- Improved project documentation

### v1.1.0

Improvements:

- Migrated database from H2 to PostgreSQL
- Added entity validation using Bean Validation
- Added PUT endpoints for ingredients and recipes
- Added database constraint to prevent duplicate ingredients
- Improved persistence configuration
