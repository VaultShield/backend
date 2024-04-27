FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/encryption-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-jar", "encryption-0.0.1-SNAPSHOT.jar"]
