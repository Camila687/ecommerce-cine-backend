# Usa una imagen oficial de OpenJDK
FROM openjdk:17-jdk-slim

# Directorio dentro del contenedor
WORKDIR /app

# Copia el .jar
COPY target/ecommerce-backend-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para correr el .jar
ENTRYPOINT ["java", "-jar", "app.jar"]