# First stage for building the application
FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src src
RUN mvn -B package -DskipTests

# Second stage for running the application
FROM amazoncorretto:17.0.7-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar spring-boot-admin.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring-boot-admin.jar"]