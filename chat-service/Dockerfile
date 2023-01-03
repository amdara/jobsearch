FROM openjdk:11
ADD ./target/chat-service.jar /usr/src/chat-service.jar
WORKDIR /usr/src
ENTRYPOINT ["java","-jar", "chat-service.jar"]