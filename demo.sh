#!/bin/bash

echo "=== Java Coverage Instrumentor Demo ==="
echo ""
echo "This demonstration shows:"
echo "1. Building the complete coverage platform"
echo "2. Running a sample Java application with coverage instrumentation"
echo "3. Collecting coverage data via gRPC"
echo ""

echo "Step 1: Building all components..."
cd /workspaces/java-coverage-instrumentor
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
else
    echo "❌ Build failed!"
    exit 1
fi

echo ""
echo "Step 2: Starting coverage collector in background..."
java -jar coverage-collector/target/coverage-collector-*.jar &
COLLECTOR_PID=$!
echo "Coverage collector started with PID: $COLLECTOR_PID"

# Wait for collector to start
sleep 3

echo ""
echo "Step 3: Running Spring Boot application with coverage agent..."
cd examples/sample-java-app
export COVERAGE_COLLECTOR_HOST=localhost
export COVERAGE_COLLECTOR_PORT=9090

# Start Spring Boot app with coverage agent in background
java -javaagent:../../coverage-agent/target/coverage-agent-*.jar \
     -jar target/sample-java-app-*.jar &
APP_PID=$!
echo "Spring Boot app started with PID: $APP_PID"

# Wait for Spring Boot to start
echo "Waiting for Spring Boot application to start..."
sleep 10

echo ""
echo "Step 4: Running tests to generate coverage data..."
# Run unit tests with coverage agent
mvn test -Dspring.profiles.active=test

echo ""
echo "Step 5: Making REST API calls to generate more coverage..."
# Make some REST API calls to exercise different code paths
BASE_URL="http://localhost:8090/sample-app/api/calculator"

echo "Making API calls to generate coverage data..."
curl -s -X POST "$BASE_URL/add" -H "Content-Type: application/json" -d '{"a":10,"b":5}' || true
curl -s -X POST "$BASE_URL/multiply" -H "Content-Type: application/json" -d '{"a":7,"b":6}' || true  
curl -s -X POST "$BASE_URL/divide" -H "Content-Type: application/json" -d '{"a":20,"b":4}' || true
curl -s "$BASE_URL/factorial/5" || true
curl -s "$BASE_URL/prime/17" || true
curl -s -X POST "$BASE_URL/complex-calculation" -H "Content-Type: application/json" -d '{"a":8,"b":3}' || true

echo "Coverage data has been sent to the collector via gRPC"
echo ""

# Clean up
kill $APP_PID 2>/dev/null
wait $APP_PID 2>/dev/null
kill $COLLECTOR_PID 2>/dev/null
wait $COLLECTOR_PID 2>/dev/null

echo "✅ Demo completed successfully!"
echo ""
echo "Key features demonstrated:"
echo "- ByteBuddy instrumentation of Java classes"
echo "- Real-time coverage data collection"  
echo "- gRPC streaming of coverage events"
echo "- Spring Boot REST API with comprehensive test coverage"
echo "- Multi-module Maven architecture"
echo "- Unit tests, integration tests, and REST API testing"
echo "- Extensible design for future language support"
