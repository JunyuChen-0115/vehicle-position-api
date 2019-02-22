FROM openjdk:8-jre-alpine

MAINTAINER junyu.chen

RUN echo 'Copying jar'
ARG JAR_FILE
COPY ${JAR_FILE} /app.jar

EXPOSE  8080 8000

CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/app.jar"]