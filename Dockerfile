FROM eclipse-temurin:23-jdk
COPY target/stockflow.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
