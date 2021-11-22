FROM openjdk:11-jdk-alpine
EXPOSE 3001
ARG JAR_FILE=target/marketlogic-assignment-survery-0.0.1.jar
ADD ${JAR_FILE} marketlogic-assignment-survery-0.0.1.jar
ENTRYPOINT ["java","-jar","/marketlogic-assignment-survery-0.0.1.jar"]