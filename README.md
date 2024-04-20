# VaultShield

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](URL_TO_BUILD_STATUS)
[![Maven](https://img.shields.io/badge/Maven-3.9.6-blue.svg)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-25.0.2-blue.svg)](https://www.docker.com/)
[![Java](https://img.shields.io/badge/Java-17-red.svg)](https://openjdk.java.net/projects/jdk/11/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Welcome to [VaultShield](https://github.com/VaultShield) Java API, please follow the steps correctly and if you have any inconsistency, doubt or improvement you can create an issue using the template correctly.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- **Java JDK 17**: Make sure Java JDK 17 on your machine (you can check by running `java -version`).
- **Maven**: You need Maven installed to handle dependencies and to build the project. Install it from [here](https://maven.apache.org/install.html).
- **Docker**: You will need Docker to run the containers for the database and the application. Download it from [Docker's website](https://www.docker.com/get-started).
- **Ports**: Make sure you have port 8080 **(API)** and 5432 **(POSTGRESQL)** not used by another resource

## Installation

to install the project, follow these steps:

```bash
$ git clone https://github.com/VaultShield/backend.git
```
```bash
$ cd /backend
```
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
We have created a postman collections to test the api, just follow the steps to find it
```bash
$ cd backend/postman/
```
