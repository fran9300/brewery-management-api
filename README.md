# Brewery Management API

**Version 1.3.0 - Functional MVP**

RESTful API built with Spring Boot for managing brewery recipes and ingredients.

---

## Overview

This project demonstrates backend development concepts such as layered architecture, REST API design, DTO pattern, JPA entity relationships, validation, exception handling, and relational database management.

The API allows managing ingredients, recipes, and the relationships between them through a structured Spring Boot architecture.

---

# Features

## Ingredient Management

- Create ingredients
- Retrieve all ingredients
- Retrieve ingredients by ID
- Update ingredients
- Delete ingredients
- Input validation
- DTO-based request and response handling
- Automatic name normalization
- Prevent duplicate ingredient names
- Database integrity validation

---

## Recipe Management

- Create recipes
- Retrieve all recipes
- Retrieve recipes by ID
- Update recipes
- Delete recipes
- Associate ingredients with recipes
- Manage Many-to-Many relationships using JPA

---

## API Features

- Layered architecture
- DTO pattern implementation
- Global exception handling
- RESTful endpoint design
- PostgreSQL relational database
- Spring Data JPA persistence
- Hibernate ORM
- Bean Validation
- Database constraints
- Swagger/OpenAPI documentation
- Postman API testing collection
- Docker Compose database environment

---

# Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Maven Wrapper
- Lombok
- Bean Validation
- Swagger/OpenAPI
- Postman
- Docker

---

# Architecture

The application follows a layered architecture:

```
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL Database
```

## Project Structure

```
controller  -> REST endpoints
service     -> Business logic
repository  -> Data access layer
dto         -> Request and Response objects
model       -> JPA entities
exception   -> Custom exceptions and global error handling
```

---

# Database

The project uses PostgreSQL running through Docker Compose.

Database configuration is managed through environment variables.

Create a `.env` file:

```env
POSTGRES_DB=brewery_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=your_password

DB_URL=jdbc:postgresql://localhost:5432/brewery_db
DB_USERNAME=postgres
DB_PASSWORD=your_password
```

Start PostgreSQL:

```bash
docker compose up -d
```

Check container status:

```bash
docker ps
```

---

# Running Locally

Clone the repository:

```bash
git clone https://github.com/fran9300/brewery-management-api.git
```

Navigate to the project:

```bash
cd brewery-management-api
```

Start the application using Maven Wrapper:

### Windows

```bash
.\mvnw spring-boot:run
```

### Linux / Mac

```bash
./mvnw spring-boot:run
```

The API will be available at:

```
http://localhost:8080
```

---

# API Documentation

Swagger/OpenAPI documentation is available at:

```
http://localhost:8080/swagger-ui/index.html
```

Swagger allows exploring and testing all available endpoints directly from the browser.

---

# API Testing

The repository includes a Postman collection with predefined requests.

Included scenarios:

- Ingredient CRUD operations
- Recipe CRUD operations
- Recipe-Ingredient relationship management
- Validation scenarios
- Error handling responses

Collection:

```
postman/Brewery-Management-API.postman_collection.json
```

---

# API Endpoints

## Ingredients

| Method | Endpoint |
|--------|----------|
| GET | `/api/ingredients` |
| GET | `/api/ingredients/{id}` |
| POST | `/api/ingredients` |
| PUT | `/api/ingredients/{id}` |
| DELETE | `/api/ingredients/{id}` |

---

## Recipes

| Method | Endpoint |
|--------|----------|
| GET | `/api/recipes` |
| GET | `/api/recipes/{id}` |
| POST | `/api/recipes` |
| PUT | `/api/recipes/{id}` |
| DELETE | `/api/recipes/{id}` |
| POST | `/api/recipes/{recipeId}/ingredients/{ingredientId}` |

---

# Database Model

![ER Diagram](images/er-diagram.png)

The application uses a Many-to-Many relationship between recipes and ingredients.

Implemented through the join table:

```
recipe_ingredients
```

Relationship:

```
Recipe
    ↔ Many-to-Many ↔
Ingredient
```

---

# Project Status

Functional MVP

The API currently includes:

- Ingredient management
- Recipe management
- DTO architecture
- PostgreSQL persistence
- Docker database environment
- Validation
- Exception handling
- Swagger documentation
- Postman testing collection

---

# Roadmap

Future improvements:

- [ ] Unit testing
- [ ] Authentication and authorization
- [ ] Flyway database migrations
- [ ] CI/CD pipeline with GitHub Actions
- [ ] SonarQube quality analysis
- [ ] Automated code formatting

---

# Learning Goals

This project focuses on practicing:

- REST API design
- Spring Boot architecture
- Dependency injection
- DTO pattern
- Entity relationships with JPA
- Exception handling
- Database persistence
- Backend development best practices

---

# Release Notes

## v1.3.0

Improvements:

- Added DTO request/response architecture
- Added global error response handling
- Improved exception management
- Migrated database environment to Docker Compose
- Improved JPA relationship handling
- Improved project structure


## v1.2.0

Improvements:

- Added Swagger/OpenAPI documentation
- Added Postman collection
- Improved API testing workflow
- Improved project documentation


## v1.1.0

Improvements:

- Migrated database from H2 to PostgreSQL
- Added Bean Validation
- Added PUT endpoints
- Added database constraints
- Improved persistence configuration
