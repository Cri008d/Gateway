# Etapa 1: Construcción (builder)
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Solo copiamos el pom y el código fuente. 
# NO intentes copiar carpetas 'mvn' ni 'mvnw' que no existan.
COPY pom.xml .
COPY src ./src

# Compilamos usando el Maven que ya viene en la imagen
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copiamos el jar desde la etapa builder
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]