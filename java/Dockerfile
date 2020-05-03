FROM maven:3.5-jdk-8

COPY . /usr/src/app
WORKDIR /usr/src/app/ApiPeriodos

RUN mvn clean package

EXPOSE 8080
ENTRYPOINT java -jar target/api-periodos-1.0.0.jar