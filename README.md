# VaultShield

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](URL_TO_BUILD_STATUS)
[![Maven](https://img.shields.io/badge/Maven-3.9.6-blue.svg)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-25.0.2-blue.svg)](https://www.docker.com/)
[![Java](https://img.shields.io/badge/Java-17-red.svg)](https://openjdk.java.net/projects/jdk/11/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Welcome to [VaultShield](https://github.com/VaultShield) Java API, please follow the steps correctly and if you have any inconsistency, doubt or improvement you can create an issue using the template correctly.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- **Docker**: You will need Docker to run the containers for the database and the application. Download it from [Docker's website](https://www.docker.com/get-started).
- **Ports**: Make sure you have port 8080 **(API)**, 8085 **(Go/Export)**, 8082 **(Java/Encryption)** and 5432 **(POSTGRESQL)** not used by another resource
- No, you don't need Java or Maven! You just have to run the script correctly.

## Installation

to install the project, follow these steps:

```bash
$ git clone https://github.com/VaultShield/backend.git
```
```bash
$ cd /backend
```
be sure to fill the environment variables, look for a '.env' next to start scripts (if not, create it) and add the following configuration
```yml
JWT_SECRET=YOUR_SECRET_JWT
JWT_RECOVER=YOUR_SECRET_JWT_RECOVER
POSTGRES_PASSWORD=YOUR_SECRET_POSTGRES_PASSWORD
```
and please change the values!!

to run the latest version available under development **(optional)**
```bash
$ git switch develop
```
**Linux and macOS**:
```bash
$ chmod +x start.sh
```
```bash
$ ./start.sh
```
**Windows**:
```bash
$ ./start.ps1
```

## Swagger Docs
You can access the swagger to review also from the documentation, make sure to start the project first and not make any errors!
- http://localhost:8080/swagger-ui/index.html#/

## Postman collections
We have created Postman collections to test the API, just follow the steps to find it
```bash
$ cd backend/postman/
```
