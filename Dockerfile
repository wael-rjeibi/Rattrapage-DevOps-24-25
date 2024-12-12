FROM openjdk:17-slim

# Set environment variables to ensure non-interactive apt
ENV DEBIAN_FRONTEND=noninteractive

# Expose application port
EXPOSE 8090

# Update and install curl
RUN apt-get update && apt-get install -y curl --no-install-recommends && \
    rm -rf /var/lib/apt/lists/*

# Download the JAR file from Nexus
RUN curl -o /eventsProject-1.0.0.jar -L "http://192.168.33.10:8081/repository/maven-releases/tn/esprit/eventsProject/1.0.0/eventsProject-1.0.0.jar"

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "/eventsProject-1.0.0.jar"]
