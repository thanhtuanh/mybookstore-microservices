# Base Image mit OpenJDK 21
FROM eclipse-temurin:21-jdk-alpine

# Arbeitsverzeichnis im Container
WORKDIR /app

# Abhängigkeiten in den Container kopieren
COPY target/auth-service-*.jar app.jar

# Port, den die App nutzt
EXPOSE 8081

# Startbefehl
ENTRYPOINT ["java", "-jar", "app.jar"]
