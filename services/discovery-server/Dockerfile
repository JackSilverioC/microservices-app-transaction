FROM openjdk:17-jdk-alpine

ADD target/discovery-server-0.0.1-SNAPSHOT.jar discovery-server.jar

ENTRYPOINT ["java", "-jar", "discovery-server.jar"]