FROM openjdk:8-jre-alpine

MAINTAINER junyu.chen

RUN echo 'Copying jar'
ARG JAR_FILE
COPY ${JAR_FILE} /app.jar

ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE  8080 8000

CMD ["/usr/bin/java", "-jar", "/app.jar"]