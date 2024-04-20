FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY --from=build /app/target/vault-shield-0.0.5-SNAPSHOT.jar /app/

CMD ["java", "-jar", "vault-shield-0.0.5-SNAPSHOT.jar"]
