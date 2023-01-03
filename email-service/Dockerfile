FROM openjdk:11
ADD target/email-service.jar email-service.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "/email-service.jar"]
