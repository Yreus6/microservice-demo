FROM openjdk:15-jdk-alpine
ARG JAR_FILE=build/libs/app-customer-service-0.0.1.SNAPSHOT.jar

COPY ${JAR_FILE} customer-service.jar
ENTRYPOINT ["java", "-jar", "/customer-service.jar"]
