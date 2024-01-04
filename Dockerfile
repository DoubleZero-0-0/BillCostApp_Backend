
FROM openjdk:17-jdk
LABEL maintainer = "codeinfinity"
ARG  JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

