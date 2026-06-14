# Brewery Management API

REST API developed with Spring Boot for managing beer recipes and brewing ingredients.

## Overview

This project aims to provide a backend system for managing brewing recipes, ingredients, and production-related data. It is being developed as a learning project to practice backend development concepts using Java and Spring Boot.

## Current Features

### Ingredient Management

* Create ingredients
* List all ingredients
* Store ingredient information such as:

  * Name
  * Type
  * Quantity
  * Unit of measurement

### Recipe Management

* Create recipes
* List recipes
* Associate ingredients with recipes

## Technologies

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* Lombok

## Project Structure

```text
controller  -> REST endpoints
service     -> Business logic
repository  -> Data access layer
model       -> JPA entities
```

## Roadmap

Planned improvements:

* [ ] Complete CRUD operations for Ingredients
* [ ] Complete CRUD operations for Recipes
* [ ] PostgreSQL integration
* [ ] Swagger/OpenAPI documentation
* [ ] DTO implementation
* [ ] Validation and exception handling
* [ ] Unit testing
* [ ] Docker support

## Learning Goals

This project focuses on practicing:

* REST API design
* Layered architecture
* Dependency injection
* Entity relationships with JPA
* Backend development using Spring Boot

```
```
