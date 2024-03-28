# Starting up a Spring Boot Admin Service
## 1 - Add dependency to pom.xml
```xml
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-server</artifactId>
</dependency>
```

## 2 - Add @EnableAdminServer annotation
```java
@SpringBootApplication
@EnableAdminServer
public class SpringBootAdminApplication {

    public static void main(String[] args) { SpringApplication.run(SpringBootAdminApplication.class, args); }
}
```

## 3 - Dockerize for k8s
```
# See Dockerfile for config

# Package build
mvn package

# Build image
docker build -t kafka-demo/spring-boot-admin ../.

# See ../spring-boot-admin.yaml for k8s config

# Apply config
kubectl apply -f ../spring-boot-admin.yaml
```