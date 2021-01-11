FROM openjdk:13-jdk-alpine
WORKDIR /opt/app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","app.jar"]