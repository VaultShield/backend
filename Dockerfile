FROM postgres:latest

FROM openjdk:17
 
COPY target/password-manager-0.0.2-SNAPSHOT.jar password-manager-0.0.2-SNAPSHOT.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "password-manager-0.0.2-SNAPSHOT.jar"]

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=admin
ENV POSTGRES_DB=db
ENV JWT_TOKEN="VAULTSHIELD_KEY"

EXPOSE 5432
