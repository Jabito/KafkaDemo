FROM amazoncorretto:17.0.7-alpine

COPY target/*.jar spring-boot-admin.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring-boot-admin.jar"]