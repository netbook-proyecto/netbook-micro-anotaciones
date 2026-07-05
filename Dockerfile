# ETAPA 1: Construcción (Compilación del JAR)
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Contenedor ligero final)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/netbook-micro-anotaciones-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 5005
ENTRYPOINT ["java", "-jar", "app.jar"]

