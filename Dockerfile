# Version 1.0.0
FROM java:8

MAINTAINER LarryKoo "larrykoo@126.com"

# The environment variable
ENV APP_NAME @project.build.finalName@
ENV APP_VERSION @project.version@
ENV JAVA_OPTS=""

ADD pom.xml /tmp/build/
RUN cd /tmp/build && ./mvnw -q dependency:resolve

ADD src /tmp/build/src
        #构建应用
RUN cd /tmp/build && ./mvnw -q -DskipTests=true package \
        #拷贝编译结果到指定目录
        && mv target/*.jar /app.jar \
        #清理编译痕迹
        && cd / && rm -rf /tmp/build

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar