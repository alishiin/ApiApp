# Imagen base con JDK 17
FROM eclipse-temurin:17-jdk

LABEL authors="stago"

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el JAR de la API
COPY target/ApiApp-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto donde corre la API
EXPOSE 8080

# Comando para ejecutar la API
ENTRYPOINT ["java", "-jar", "app.jar"]
