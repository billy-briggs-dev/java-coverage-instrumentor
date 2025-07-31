# Use OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy sample application JAR
COPY examples/sample-java-app/target/sample-java-app-*.jar sample-app.jar

# Copy coverage agent JAR
COPY coverage-agent/target/coverage-agent-*.jar coverage-agent.jar

# Create directory for coverage reports
RUN mkdir -p /app/coverage

# Default command runs the application with coverage agent
CMD ["java", "-javaagent:coverage-agent.jar", "-jar", "sample-app.jar"]
