# Version 1.0.0
FROM openjdk:8-jdk-alpine

MAINTAINER LarryKoo "larrykoo@126.com"

# The environment variable
ENV APP_NAME="helloworld"
ENV APP_VERSION=""
ENV JAVA_OPTS=""

EXPOSE 8080
COPY target/$APP_NAME-*.jar app.jar
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
