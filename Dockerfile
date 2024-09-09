# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/StudentManagement-0.0.1-SNAPSHOT.jar /app/student-management.jar

# Make port 8080 available to the outside world
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "student-management.jar"]
