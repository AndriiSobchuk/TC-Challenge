# Build stage
FROM maven:3.8.4-openjdk-17-slim AS build
# Set the working directory to /app
WORKDIR /app
# Copy the POM file to the container
COPY pom.xml .
# Download all the required dependencies for the project
RUN mvn dependency:go-offline
# Copy the source code to the container
COPY src ./src
# Compile the code and create a JAR file
RUN mvn package

# Run stage
FROM openjdk:17-jre-slim-buster
# Set the working directory to /app
WORKDIR /app
# Copy the JAR file from the build stage to the container
COPY --from=build /app/target/*.jar ./app.jar
# Exsposing  port to 8080
EXPOSE 8080
# Run .jar file
CMD ["java", "-jar", "./app.jar"]

