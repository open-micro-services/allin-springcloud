#https://spring.io/guides/gs/spring-boot-docker/
FROM openjdk:8-jdk-alpine
USER PJL:PJL
ARG JAR_FILE=target/*-exe.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]