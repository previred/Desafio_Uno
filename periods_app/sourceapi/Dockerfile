#
# Build stage
#
FROM maven:3.6.0-jdk-8-slim AS build
COPY . /tmp/
WORKDIR /tmp/ApiPeriodos/
RUN mvn package

#
# Package stage
#
FROM openjdk:8-jre-slim
COPY --from=build /tmp /usr/local/api/

WORKDIR /usr/local/api

EXPOSE 8080

ENTRYPOINT ["java","-jar","./ApiPeriodos/target/api-periodos-1.0.0.jar"]