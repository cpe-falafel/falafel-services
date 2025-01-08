# BUILD
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY .. .

RUN mvn clean install -pl commons -am

RUN mvn clean package -pl flux-management -am

# EXECUTION
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=builder /app/flux-management/target/flux-management-*.jar /app/flux-management.jar

# Needed environment variables:
# - POSTGRES_SOURCE_URI
# - POSTGRES_USER
# - POSTGRES_PASSWORD
# Optional environment variables:
# - SERVER_PORT

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/flux-management.jar"]