# VaultShield

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](URL_TO_BUILD_STATUS)
[![Maven](https://img.shields.io/badge/Maven-3.9.6-blue.svg)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-25.0.2-blue.svg)](https://www.docker.com/)
[![Java](https://img.shields.io/badge/Java-17-red.svg)](https://openjdk.java.net/projects/jdk/11/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Welcome to [VaultShield](https://github.com/VaultShield) Java API, please follow the steps correctly and if you have any inconsistency, doubt or improvement you can create an issue using the template correctly.

## Before try use
Check please the deploy repository [app](https://github.com/VaultShield/app) here can you deploy and serve the complete project

## If you want deploy only backend
make sure you have **docker**!!

first fill the data in .env.example and change the name to just ".env"
(min 32 characters in JWT properties)
```yml
.\start.ps1
```
for MacOs or linux users
```yml
chmod +x start.sh
./start.sh
```

## Swagger Docs
You can access the swagger to review also from the documentation, make sure to start the project first and not make any errors!
- http://localhost:8080/swagger-ui/index.html#/

## Postman collections
We have created Postman collections to test the API, just follow the steps to find it
```bash
$ cd backend/postman/
```