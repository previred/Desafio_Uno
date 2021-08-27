FROM adoptopenjdk/maven-openjdk11 AS build
COPY . /root
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
RUN mvn -T 1C -f /root/pom.xml clean package

FROM adoptopenjdk/openjdk11:alpine-slim
COPY --from=build /root/target/missing-dates-*.jar app.jar
EXPOSE 8081
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "app.jar"]
