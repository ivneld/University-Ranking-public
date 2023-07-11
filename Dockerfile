FROM ubuntu:latest
LABEL authors="kimyuseong"

FROM amazoncorretto:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=rds","/app.jar"]

