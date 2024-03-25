FROM amazoncorretto:17.0.7-alpine

COPY target/*.jar kafka-producer.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "kafka-producer.jar"]