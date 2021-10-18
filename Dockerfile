FROM openjdk:8-jdk-alpine
COPY target/desafiouno-0.0.1-SNAPSHOT.jar desafiouno.jar
ENTRYPOINT ["java","-jar","/desafiouno.jar"]