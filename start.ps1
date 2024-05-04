Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass -Force

Write-Host "Building Docker images and starting services with Docker Compose..."
docker-compose up --build -d

# verify the operation
if ($LASTEXITCODE -eq 0) {
    Write-Host "Docker containers are up and running." -ForegroundColor Green
} else {
    Write-Host "Failed to start Docker containers. Exit code: $LASTEXITCODE" -ForegroundColor Red
}