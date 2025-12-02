# Etapa 1: Build con Maven y JDK 17
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos archivos de Maven
COPY pom.xml .
COPY src ./src

# Construimos el JAR (sin tests)
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera para correr el JAR
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiamos el JAR desde la etapa de build
COPY --from=build /app/target/ApiApp-0.0.1-SNAPSHOT.jar app.jar

# Configuramos las variables de entorno para Supabase
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db.pnpyntvkfpwtgbzcisti.supabase.co:5432/postgres
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=wwe1213zX123

# Exponemos el puerto
EXPOSE 8080

# Ejecutamos la app
ENTRYPOINT ["java", "-jar", "app.jar"]
