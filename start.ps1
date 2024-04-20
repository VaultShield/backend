Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass -Force

Write-Host "Running Maven clean install..."
mvn clean install -DskipTests

# verificacion de maven build
if ($LASTEXITCODE -eq 0) {
    Write-Host "Maven build succeeded."
    
    Write-Host "Building Docker images..."
    docker-compose build

    # Verificacion de Docker build
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Docker images built successfully."
        Write-Host "Starting services with Docker Compose..."
        docker-compose up -d
    } else {
        Write-Host "Docker build failed with exit code $LASTEXITCODE" -ForegroundColor Red
    }
} else {
    Write-Host "Maven build failed with exit code $LASTEXITCODE" -ForegroundColor Red
}