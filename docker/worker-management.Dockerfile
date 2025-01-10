# BUILD
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY .. .

RUN mvn clean install -pl commons -am -Dmaven.test.skip=true

RUN mvn clean package -pl worker-management -am -Dmaven.test.skip=true

# EXECUTION
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=builder /app/worker-management/target/worker-management-*.jar /app/worker-management.jar

# Needed environment variables:
# - POSTGRES_SOURCE_URI
# - POSTGRES_USER
# - POSTGRES_PASSWORD
# - FLUX_MANAGER_URL
# Optional environment variables:
# - SERVER_PORT

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/worker-management.jar"]