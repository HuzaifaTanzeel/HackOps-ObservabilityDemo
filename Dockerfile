# ---------- Stage 1: Build ----------
FROM maven:3.9.8-eclipse-temurin-17 AS builder

WORKDIR /app
COPY . .

# Build and rename final JAR to app.jar
RUN mvn clean package -DskipTests -DfinalName=app

# ---------- Stage 2: Run ----------
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy built JAR from previous stage
COPY --from=builder /app/target/app.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]
