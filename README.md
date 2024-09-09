# Student Management System

## Overview

The Student Management System is a Spring Boot application designed to manage student information. It provides authentication and authorization features using JWT, integrates with MongoDB for data storage, and offers a user-friendly interface through Swagger for API documentation.

## Features

- **Authentication**: Secure login using JWT.
- **Authorization**: Role-based access control (USER and ADMIN).
- **Student Management**: CRUD operations for student records.
- **API Documentation**: Auto-generated Swagger UI for API endpoints.

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java 17**: Required for running the Spring Boot application.
- **Docker**: For building and running the application in a container.
- **MongoDB**: Database for storing student records.

## Getting Started

### Running Locally

1. **Clone the Repository**

   
sh
   git clone <repository-url>
   cd StudentManagement

Build the Application

Navigate to the project directory and use Maven to build the application.

sh
mvn clean install
Run the Application

You can run the application using Maven:

sh
mvn spring-boot:run
Alternatively, if you prefer running the JAR file directly:

sh
java -jar target/StudentManagement-0.0.1-SNAPSHOT.jar
The application will be available at http://localhost:8080

Docker Setup
Build the Docker Image

Ensure you are in the directory containing the Dockerfile, then build the Docker image:

sh
docker build -t student-management-app .
Run the Docker Container

Start the Docker container and map it to port 8080 on your host:

sh
docker run -p 8080:8080 student-management-app
If port 8080 is already in use, you can use a different port:

sh
docker run -p 9090:8080 student-management-app

API Endpoints
The application provides the following endpoints:

Authentication

POST /authenticate - Generates a JWT token.
Students

GET /students - List all students.
GET /students/new - Show registration form (ADMIN only).
POST /students - Register a new student (ADMIN only).
GET /students/{id} - Get student details (USER only).
GET /students/{id}/edit - Show edit form (USER only).
POST /students/{id} - Update student details (USER only).
GET /students/{id}/delete - Delete a student (ADMIN only).
Swagger Documentation
API documentation is available at http://localhost:8080/swagger-ui/index.html when running locally, or http://localhost:9090/swagger-ui/index.html if using a different port.
