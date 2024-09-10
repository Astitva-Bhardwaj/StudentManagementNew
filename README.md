
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
   ```sh
   git clone <repository-url>
   cd StudentManagement
   ```

2. **Build the Application**
   ```sh
   mvn clean install
   ```

3. **Run the Application**
   Using Maven:
   ```sh
   mvn spring-boot:run
   ```
   Or, run the JAR file directly:
   ```sh
   java -jar target/StudentManagement-0.0.1-SNAPSHOT.jar
   ```

   The application will be accessible at `http://localhost:8080`

### Docker Setup
1. **Build the Docker Image**
   ```sh
   docker build -t student-management-app .
   ```

2. **Run the Docker Container**
   ```sh
   docker run -p 8080:8080 student-management-app
   ```

   If port 8080 is already in use:
   ```sh
   docker run -p 9090:8080 student-management-app
   ```

## API Endpoints

### Authentication
- `POST /authenticate`: Generate a JWT token.

### Students
- `GET /students`: List all students.
- `GET /students/new`: Show registration form (ADMIN only).
- `POST /students`: Register a new student (ADMIN only).
- `GET /students/{id}`: Get student details (USER only).
- `GET /students/{id}/edit`: Show edit form (USER only).
- `POST /students/{id}`: Update student details (USER only).
- `GET /students/{id}/delete`: Delete a student (ADMIN only).

## API Documentation
API documentation is available at:
- `http://localhost:8080/swagger-ui/index.html` when running locally
- `http://localhost:9090/swagger-ui/index.html` if using a different port
