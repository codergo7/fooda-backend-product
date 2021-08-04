FROM openjdk:11
MAINTAINER "yilmaz@mail.be"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
