FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar ewm-gateway-service.jar
ENTRYPOINT ["java","-jar","/ewm-gateway-service.jar"]