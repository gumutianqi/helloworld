# Version 1.0.0
FROM openjdk:8-jdk-alpine

MAINTAINER LarryKoo "larrykoo@126.com"

# The environment variable
ENV APP_NAME="helloworld"
ENV APP_VERSION=""
ENV JVM_LOG_HOME="/var/logs/$APP_NAME"

## example  -Djava.security.egd=file:/dev/./urandom -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -XX:+PrintGCDetails -XX:+PrintGCApplicationStoppedTime -Xloggc:$JVM_LOG_HOME/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$JVM_LOG_HOME/heapdump.hprof
ENV JAVA_OPTS=""

## example " -server -Xms512m -Xmx1024m -Xmn128m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -Xss256K -XX:SurvivorRatio=8 "
ENV JAVA_MEM_OPTS=""


EXPOSE 8080
COPY target/$APP_NAME-*.jar app.jar
ENTRYPOINT exec java $JAVA_MEM_OPTS $JAVA_OPTS -jar /app.jar
