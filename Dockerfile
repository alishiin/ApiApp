# Usamos imagen con Maven y JDK 17
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos los archivos de Maven
COPY pom.xml .
COPY src ./src

# Construimos el JAR
RUN mvn clean package -DskipTests

# Segunda etapa: imagen más ligera para correr el JAR
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiamos el JAR generado desde la etapa build
COPY --from=build /app/target/ApiApp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
