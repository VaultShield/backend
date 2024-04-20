#!/bin/bash

echo "Running Maven clean install..."
mvn clean install -DskipTests

if [ $? -eq 0 ]; then
    echo "Maven build succeeded."
    echo "Building Docker images..."
    docker-compose build

    if [ $? -eq 0 ]; then
        echo "Docker images built successfully."

        echo "Starting services with Docker Compose in detached mode..."
        docker-compose up -d
    else
        echo "Docker build failed with exit code $?" >&2
    fi
else
    echo "Maven build failed with exit code $?" >&2
fi
