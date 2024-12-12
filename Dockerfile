FROM openjdk:17-slim

EXPOSE 8090

# Install curl
RUN apt-get update && apt-get install -y curl

# Download the JAR file from Nexus
RUN curl -o /eventsProject-1.0.0.jar -L "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/eventsProject/1.0.0/eventsProject-1.0.0.jar"
# Set the entry point
ENTRYPOINT ["java", "-jar", "/eventsProject-1.0.0.jar"]

