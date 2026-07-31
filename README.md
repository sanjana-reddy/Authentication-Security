# Authentication & Security Implementation
## Project Information
**Project Title:** Authentication & Security Implementation

**Objective**

The objective of this project is to implement secure authentication and authorization mechanisms using Spring Security and JWT (JSON Web Token) to protect REST APIs and enforce Role-Based Access Control (RBAC).

---
## Technologies Used

- Java 17
- Spring Boot 3.5.3
- Spring Security
- Spring Data JPA
- JWT (JJWT)
- MySQL 8
- Maven
- Swagger / OpenAPI
- Postman
- JUnit 5
- Mockito
- IntelliJ IDEA

---

## Project Structure

```
authentication-security
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.unitedtekinfo.authsecurity
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── entity
│   │   │       ├── exception
│   │   │       ├── repository
│   │   │       ├── security
│   │   │       └── service
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│       ├── controller
│       ├── integration
│       ├── security
│       └── service
│
├── Authentication-Security.postman_collection.json
├── README.md
├── pom.xml
└── mvnw
```

---

## 1. Project Setup & Security Configuration

Completed Tasks

- Spring Boot project created using Maven
- Spring Security configured
- Spring Data JPA configured
- MySQL database connected
- JWT dependency added
- Swagger/OpenAPI integrated
- BCrypt Password Encoder configured
- Stateless session management implemented

### Database

Database Name

```
auth_security
```

Tables

#### users

| Column |
|----------|
| id |
| username |
| email |
| password |

#### roles

| Column |
|----------|
| id |
| name |

Roles

- ROLE_ADMIN
- ROLE_MANAGER
- ROLE_CUSTOMER

#### user_roles

Many-to-Many relationship between users and roles.

---

## 2. JWT Authentication

Implemented Features

- User Registration API
- User Login API
- JWT Token Generation
- JWT Token Validation
- JWT Authentication Filter
- Stateless Authentication
- Token Expiration Configuration

JWT Configuration

```properties
jwt.secret=MySecretKeyForJwtAuthenticationProject2026SpringBootSecurity
jwt.expiration=86400000
```

---

## 3. Role-Based Authorization (RBAC)

Implemented Roles

| Role | Access |
|------|--------|
| ADMIN | Admin + Manager + Customer APIs |
| MANAGER | Manager + Customer APIs |
| CUSTOMER | Customer APIs Only |

Protected Endpoints

| Endpoint | Access |
|------------|-------------------------|
| GET /api/customer | CUSTOMER, MANAGER, ADMIN |
| GET /api/manager | MANAGER, ADMIN |
| GET /api/admin | ADMIN |

Authorization successfully tested for all user roles.

---

## 4. API Security Enhancements

Implemented

- Spring Security Filter Chain
- JWT Authentication Filter
- BCrypt Password Encryption
- Global Exception Handling
- Request Validation
- Stateless Session Management
- CSRF Disabled (REST API)
- CORS Configuration
- Unauthorized Request Handling
- Forbidden Request Handling

---

## 5. REST API Endpoints

### Authentication APIs

#### Register User

POST

```
/api/auth/register
```

Example Request

```json
{
    "username":"john",
    "email":"john@gmail.com",
    "password":"password123"
}
```

---

#### Login User

POST

```
/api/auth/login
```

Example Request

```json
{
    "email":"john@gmail.com",
    "password":"password123"
}
```

Example Response

```json
{
    "token":"<JWT_TOKEN>",
    "type":"Bearer",
    "username":"john@gmail.com",
    "email":"john@gmail.com"
}
```

---

#### Customer API

GET

```
/api/customer
```

Authorization

```
Bearer <JWT_TOKEN>
```

---

#### Manager API

GET

```
/api/manager
```

Authorization

```
Bearer <JWT_TOKEN>
```

---

#### Admin API

GET

```
/api/admin
```

Authorization

```
Bearer <JWT_TOKEN>
```

---

## Password Encryption

Passwords are encrypted using BCrypt before storing in the database.

Example

```
$2a$10$...
```

Plain text passwords are never stored.

---

## Swagger Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

Swagger was configured to document all REST APIs and support API testing.

---

## Testing

### Unit Testing

Implemented using

- JUnit 5
- Mockito

Test Classes

- AuthControllerTest
- AuthServiceTest
- JwtServiceTest

Unit Test Scenarios

- Register User Successfully
- Duplicate Email Registration
- Login Success
- Login Failure
- JWT Generation
- JWT Validation
- JWT Username Extraction

---

### Integration Testing

Implemented using Spring Boot Test and MockMvc.

Integration Test

- SecurityIntegrationTest

Scenarios Tested

- Access Customer API without Token
- Access Manager API without Token
- Access Admin API without Token

Protected endpoints correctly denied unauthorized access according to the configured security rules.

---

### Manual Testing

Manual testing performed using:

- Postman
- Swagger UI

Scenarios Tested

- User Registration
- User Login
- JWT Generation
- JWT Validation
- Customer API Access
- Manager API Access
- Admin API Access
- Customer Accessing Admin API (Forbidden)
- Invalid Login
- Duplicate Registration

---

## Running the Application

### Prerequisites

- Java 17
- Maven
- MySQL 8
- IntelliJ IDEA

### Database

Create database

```sql
CREATE DATABASE auth_security;
```

Update

```
application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/auth_security
spring.datasource.username=root
spring.datasource.password=Root@12345
```

Run

```bash
mvn clean install
```

Start the application

Run

```
AuthenticationSecurityApplication.java
```

---

## Deliverables Completed

| Deliverables 
|-------------
| User Registration API 
| User Login API 
| JWT Token Generation 
| JWT Token Validation 
| Spring Security Configuration 
| Role-Based Access Control (RBAC) 
| BCrypt Password Encryption 
| Secured REST APIs
| Swagger/OpenAPI Documentation 
| Postman Collection 
| Unit Tests 
| Integration Tests 
| Source Code Repository 
| Deployment & Configuration Guide 

---

## Expected Outcomes Achieved

- Implemented Spring Boot and Spring Security best practices.
- Implemented secure password storage using BCrypt.
- Protected REST APIs using JWT authentication.
- Enforced role-based authorization consistently.
- Followed a modular project structure.
- Applied clean coding practices.
- Implemented unit and integration testing.
- Verified API functionality using Postman and Swagger.

---

# Conclusion

This project successfully implements a secure authentication and authorization system using Spring Boot, Spring Security, JWT and MySQL. The application provides secure REST APIs with role-based access control, encrypted password storage, JWT-based authentication, comprehensive testing and interactive API documentation through Swagger.

---

# Author

**Sanjana Mutyala**