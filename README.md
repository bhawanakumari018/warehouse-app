# Warehouse Application

## Overview

This is a Spring Boot based Warehouse Management Application.

The application manages:

- Articles
- Prices
- Products
- Product Articles (relationship between Product and Article)

A Product is composed of one or more Articles along with the quantity required. The product price is calculated dynamically based on the price of individual Articles and their required quantities.

---

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database
- Maven
- JUnit 5
- MockMvc

---

## Project Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
H2 Database
```

### Layers

- Controller: Handles HTTP requests and responses.
- Service: Contains business logic.
- Repository: Handles database access.
- Database: Stores application data.

---

## Authentication

The API is secured using HTTP Basic Authentication.

### Credentials

```text
Username: admin
Password: admin123
```

### Security Features

- Spring Security
- Basic Authentication
- Protected REST APIs

Example:

Without credentials:

```http
401 Unauthorized
```

With valid credentials:

```http
200 OK
```

---

## Database

The application uses an H2 in-memory database.

### H2 Console

```text
http://localhost:8080/h2-console
```

### H2 Connection Details

```text
JDBC URL: jdbc:h2:mem:warehousedb
Username: sa
Password:
```

---

## Entity Model

### Article

Represents an individual component available in the warehouse.

Attributes:

- articleId
- name
- stock

Example:

```json
{
  "articleId": "A1",
  "name": "Leg",
  "stock": 100
}
```

---

### Price

Stores the price of an individual article.

Attributes:

- articleId
- price

Example:

```json
{
  "articleId": "A1",
  "price": 10.00
}
```

---
### Product

Represents a finished product exposed through a DTO-based API response.

API Response Attributes:

- prodId
- productName
- articles
- price

Example:

```json
{
  "prodId": "P1",
  "productName": "Table",
  "articles": [
    {
      "id": "A1",
      "artName": "Leg",
      "count": 4
    },
    {
      "id": "A2",
      "artName": "Board",
      "count": 1
    }
  ],
  "price": 60.00
}
```

---

### ProductArticle

Represents the relationship between Product and Article along with the quantity required.

Attributes:

- id
- product
- article
- quantityRequired

Example:

```json
{
  "product": {
    "productId": "P1"
  },
  "article": {
    "articleId": "A1"
  },
  "quantityRequired": 4
}
```

---

## Relationship Design

A Product can contain multiple Articles.

An Article can be used by multiple Products.

To support storing the quantity required, a separate ProductArticle entity is used.

Although the API exposes Articles directly within a Product response, the database stores the relationship using a ProductArticle entity.

This approach avoids duplicating Article information across multiple Products and keeps the database normalized.

```text
Product
   |
   |
ProductArticle
   |
   |
Article
```

Example:

```text
Table
 ├── 4 Legs
 └── 1 Board
```

---
## API Response Design

The assignment sample payload presents a Product together with its Articles and calculated price.

Internally, the application uses a normalized database structure:

```text
Product
   |
ProductArticle
   |
Article
```

To expose an API response similar to the assignment example while keeping the database normalized, DTOs are used:

- ProductResponseDto
- ArticleResponseDto

This approach separates persistence entities from API responses and provides a cleaner representation for API consumers.

---

## API Endpoints
### Articles

```http
GET    /articles
GET    /articles/{id}
POST   /articles
PUT    /articles/{id}
DELETE /articles/{id}
```

### Prices

```http
GET    /prices
GET    /prices/{id}
POST   /prices
PUT    /prices/{id}
DELETE /prices/{id}
```

### Products

```http
GET    /products
GET    /products/{id}
GET    /products/{id}/price
POST   /products
PUT    /products/{id}
DELETE /products/{id}
```
### Product Response Example

GET /products/P1

```json
{
  "prodId": "P1",
  "productName": "Table",
  "articles": [
    {
      "id": "A1",
      "artName": "Leg",
      "count": 4
    },
    {
      "id": "A2",
      "artName": "Board",
      "count": 1
    }
  ],
  "price": 60.00
}
```
### Product Articles

```http
GET    /product-articles
GET    /product-articles/{id}
POST   /product-articles
PUT    /product-articles/{id}
DELETE /product-articles/{id}
```

---

## Product Price Calculation

The product price is calculated dynamically.

Formula:

```text
Total Product Price =
Σ (Article Price × Quantity Required)
```

Example:

```text
Product: Table

Leg Price   = 10
Quantity    = 4

Board Price = 20
Quantity    = 1
```

Calculation:

```text
(10 × 4) + (20 × 1)
= 40 + 20
= 60
```

API:

```http
GET /products/P1/price
```

Response:

```json
60
```

---

## Sample Data Initialization

Sample data is automatically loaded during application startup using Spring Boot CommandLineRunner.

The following records are created automatically:

### Articles

```text
A1 - Leg - Stock 100
A2 - Board - Stock 50
```

### Prices

```text
A1 - 10
A2 - 20
```

### Products

```text
P1 - Table
```

### Product Articles

```text
P1 -> A1 -> Quantity 4
P1 -> A2 -> Quantity 1
```

This allows the application to be used immediately after startup without manually creating data.

---

## Design Decisions

### Why ProductArticle?

The assignment requires a Product to contain Articles together with the quantity required to build the Product.

The quantity does not belong exclusively to Product or Article. Instead, it belongs to the relationship between them.

For this reason, a ProductArticle entity was introduced.

Benefits:

- Avoids duplication of Article data
- Supports many-to-many relationships
- Stores quantity in the correct location
- Maintains a normalized database design
- Allows Articles to be reused across multiple Products

### Why DTOs?

The assignment sample payload embeds Articles directly inside a Product response.

Internally, the application uses Product, Article, Price, and ProductArticle entities to maintain a normalized relational model.

DTOs are used to transform the internal entity model into an API response format that closely matches the assignment requirements while keeping persistence concerns separate from API concerns.

Benefits:

- Cleaner API responses
- Separation of concerns
- No exposure of internal database structure
- Easier future API evolution

The resulting Product API response closely follows the assignment example:

```json
{
  "prodId": "P1",
  "productName": "Table",
  "articles": [
    {
      "id": "A1",
      "artName": "Leg",
      "count": 4
    }
  ],
  "price": 60.00
}
```

## Build and Run

### Run Tests

```bash
mvn clean test
``