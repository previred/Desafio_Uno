FROM frolvlad/alpine-oraclejre8:slim
VOLUME /opt/proyecto/api-ejemplo

# Set environment
ENV JAVA_HOME /usr
ENV PATH ${PATH}:/usr/bin
ENV JAVA_OPTS -javaagent:/opt/newrelic/newrelic.jar

RUN apk add -U tzdata
RUN cp /usr/share/zoneinfo/America/Santiago /etc/localtime
RUN mkdir -p /api-ejemplo/lib/
RUN mkdir -p /api-ejemplo/config/
RUN mkdir -p /api-ejemplo/logs/
ARG JAR_FILE
ADD ${JAR_FILE} /api-ejemplo/lib/app.jar
ADD bootstrap.yml /api-ejemplo/config
# Solo ambiente produccion
#ADD ./newrelic/newrelic.jar /opt/newrelic/newrelic.jar
#ADD ./newrelic/newrelic.yml /opt/newrelic/newrelic.yml
WORKDIR /api-ejemplo
ENTRYPOINT #entrypoint#