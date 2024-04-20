#!/bin/bash

echo "Building Docker images and starting services with Docker Compose..."

docker-compose up --build -d

# verificar la operación
if [ $? -eq 0 ]; then
    echo "Docker containers are up and running."
else
    echo "Failed to start Docker containers. Exit code: $?" >&2
fi
