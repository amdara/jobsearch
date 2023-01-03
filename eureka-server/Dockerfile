FROM openjdk:11
ADD target/eureka-server.jar eureka-server.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "/eureka-server.jar"]
