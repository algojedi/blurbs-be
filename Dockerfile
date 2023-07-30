# Use a base image with Java 11 
FROM openjdk:11

COPY target/*.jar app.jar
# COPY start-app.sh .

# RUN chmod +x start-app.sh

# ENTRYPOINT ["./start-app.sh"]
ENTRYPOINT ["java", "-jar", "/app.jar"]

