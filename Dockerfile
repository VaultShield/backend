FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/vault-shield-0.1.0.jar /app/

CMD ["java", "-jar", "vault-shield-0.1.0.jar"]
