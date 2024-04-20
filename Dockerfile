FROM maven:3.9.6-openjdk-17-slim as build

WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/vault-shield-0.0.5-SNAPSHOT.jar /app/

CMD ["java", "-jar", "vault-shield-0.0.5-SNAPSHOT.jar"]
