# Stage 1: build the jar (static/ resources get bundled in automatically)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: run it on a slim JRE
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 4000 9090
ENTRYPOINT ["java", "-jar", "app.jar"]
