FROM openjdk:11
ADD target/api-gateway.jar api-gateway.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "/api-gateway.jar"]
