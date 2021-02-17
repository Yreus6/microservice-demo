FROM openjdk:15-jdk-alpine
ARG JAR_FILE=build/libs/app-api-gateway-0.0.1.SNAPSHOT.jar

COPY ${JAR_FILE} api-gateway.jar
ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]
