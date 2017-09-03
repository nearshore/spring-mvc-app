FROM java:8-jdk
MAINTAINER Romel R. Sandoval <romelsandoval@gmail.com>

ENV JAVA_HOME              /usr/lib/jvm/java-8-openjdk-amd64
ENV JAVA_OPTS              ""
ENV PATH                   $PATH:$JAVA_HOME/bin

ENV SPRING_PROFILES_ACTIVE test

COPY build/libs/app.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["/bin/sh", "-c", "java $JAVA_OPTS -jar /app/app.jar --spring.profiles.active=$SPRING_PROFILES_ACTIVE"]
