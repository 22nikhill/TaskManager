FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
COPY --from=build /app/target/spring-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]