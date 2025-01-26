# Waste Sorting Management API

A RESTful API for managing waste categories, disposal guidelines, and recycling tips, developed with Spring Boot for Enviro365's environmental education platform.

## Features

- Full CRUD operations for waste categories, disposal guidelines, and recycling tips
- Input validation with meaningful error messages
- In-memory H2 database with console access
- REST best practices with proper HTTP status codes
- Swagger documentation (OpenAPI 3.0)

## Technologies

- Java 17
- Spring Boot 3.4.2
- H2 Database (In-memory)
- Maven
- Lombok
- Hibernate Validator
- Spring Data JPA

## Prerequisites

- Java 17 JDK
- Maven 3.8+
- Postman or API client

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/lethaboletsoalo/waste-sorting-api.git
   ```

2. Build and run:
   ```bash
   mvn spring-boot:run
   ```

## Database Access

- H2 Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:envirodb`
- Username: `sa`
- Password: (leave empty)

## API Endpoints

### Waste Categories

| Method | Endpoint                      | Description                     |
|--------|-------------------------------|---------------------------------|
| GET    | `/api/waste-categories`       | Get all waste categories        |
| GET    | `/api/waste-categories/{id}`  | Get category by ID              |
| POST   | `/api/waste-categories`       | Create new waste category       |
| PUT    | `/api/waste-categories/{id}`  | Update existing category        |
| DELETE | `/api/waste-categories/{id}`  | Delete category                 |

### Disposal Guidelines

| Method | Endpoint                                  | Description                      |
|--------|-------------------------------------------|----------------------------------|
| GET    | `/api/disposal-guidelines`                | Get all guidelines               |
| GET    | `/api/disposal-guidelines/{id}`           | Get guideline by ID              |
| GET    | `/api/disposal-guidelines/category/{categoryId}` | Get guidelines by category|
| POST   | `/api/disposal-guidelines`                | Create new guideline             |
| PUT    | `/api/disposal-guidelines/{id}`           | Update guideline                 |
| DELETE | `/api/disposal-guidelines/{id}`           | Delete guideline                 |

### Recycling Tips

| Method | Endpoint                              | Description                      |
|--------|---------------------------------------|----------------------------------|
| GET    | `/api/recycling-tips`                 | Get all recycling tips           |
| GET    | `/api/recycling-tips/{id}`            | Get tip by ID                    |
| GET    | `/api/recycling-tips/category/{categoryId}` | Get tips by category       |
| POST   | `/api/recycling-tips`                 | Create new tip                   |
| PUT    | `/api/recycling-tips/{id}`            | Update tip                       |
| DELETE | `/api/recycling-tips/{id}`            | Delete tip                       |

## Testing

Run tests:
```bash
mvn test
```

## Error Handling

Common error codes:
- 400 Bad Request: Invalid input data
- 404 Not Found: Resource not found
- 409 Conflict: Duplicate category name
- 500 Internal Server Error: Unexpected server error

## Data Model

```
WasteCategory
├── id (PK)
├── name (Unique)
├── description
├── disposalGuidelines (OneToMany)
└── recyclingTips (OneToMany)

DisposalGuideline
├── id (PK)
├── guideline
└── category (ManyToOne)

RecyclingTip
├── id (PK)
├── tip
└── category (ManyToOne)
```

## Example API Calls

Get Waste Categories:
```bash
curl -X GET http://localhost:8080/api/waste-categories
```

Create Recycling Tip:
```bash
curl -X POST -H "Content-Type: application/json" \
-d '{"tip":"Clean containers first","categoryId":1}' \
http://localhost:8080/api/recycling-tips
```
