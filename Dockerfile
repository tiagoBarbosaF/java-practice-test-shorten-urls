FROM openjdk:17
ADD target/test-dev-0.0.1.jar test-dev.jar
ENTRYPOINT ["java", "-jar", "/test-dev.jar"]