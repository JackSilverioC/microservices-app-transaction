FROM openjdk:17-jdk-alpine

# Instala curl
RUN apk add --no-cache curl

ADD target/config-server-0.0.1-SNAPSHOT.jar config-server.jar

ENTRYPOINT ["java", "-jar", "config-server.jar"]