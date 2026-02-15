# Use Java 17 JDK on Alpine for a small image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the fat JAR produced by Maven Shade plugin
COPY target/*-shaded.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java","-jar","app.jar"]
