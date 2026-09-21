FROM maven:3.9.11-eclipse-temurin-25-alpine AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Configurações do banco de dados comentadas pois já estão definidas no compose.yaml
# ENV SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/produtos
# ENV SPRING_DATASOURCE_USERNAME=root
# ENV SPRING_DATASOURCE_PASSWORD=

ENTRYPOINT ["java", "-jar", "app.jar"]
