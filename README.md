<div align="center">

# 📋 TaskFlow API

### A RESTful API for task management — built with Java, Spring Boot & MySQL

*Clean architecture • REST best practices • Interactive Swagger docs*

<br/>

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![License](https://img.shields.io/badge/license-MIT-blue?style=for-the-badge)

<br/>

<img src="https://skillicons.dev/icons?i=java,spring,mysql,maven,git,github" alt="Tech stack icons" />

</div>

<br/>

## 📑 Table of Contents

- [🧭 Overview](#-overview)
- [⚙️ Tech Stack](#️-tech-stack)
- [🏗️ Architecture](#️-architecture)
- [🧠 Practices Adopted](#-practices-adopted)
- [🗄️ Database Configuration](#️-database-configuration-mysql)
- [📡 API Endpoints](#-api-endpoints)
- [🔎 Example Requests](#-example-requests)
- [📘 Swagger Documentation](#-swagger-documentation)
- [🚀 How to Run the Project](#-how-to-run-the-project)
- [🚧 Improvements under construction](#-improvements-under-construction)
- [🧩 Design Decisions](#-design-decisions)
- [📝 Notes](#-notes)
- [✅ Final Result](#-final-result)

<br/>

## 🧭 Overview

> **TaskFlow API** is a backend system for managing tasks (To-Do), providing full **CRUD** operations with a structured architecture, database persistence using **MySQL**, and interactive API documentation via **Swagger**.

The project is designed with **scalability**, **maintainability**, and **clean code** practices in mind, making it a solid reference template for future backend projects.

<br/>

## ⚙️ Tech Stack

<div align="left">
<img src="https://skillicons.dev/icons?i=java,spring,mysql,maven" alt="Tech stack" />
</div>

| Technology | Purpose |
|---|---|
| ☕ **Java 17+** | Core programming language |
| 🌱 **Spring Boot** | Application framework |
| 🌐 **Spring Web** | REST API layer |
| 🗃️ **Spring Data JPA** | Data persistence abstraction |
| 🐘 **Hibernate** | ORM implementation |
| 🐬 **MySQL** | Relational database |
| 📦 **Maven** | Dependency & build management |
| 📘 **Swagger / OpenAPI 3** | Interactive API documentation |

<br/>

## 🏗️ Architecture

The project follows a **layered architecture**:

```
┌────────────────────┐
│     Controller      │  → HTTP request handling
├────────────────────┤
│       Service       │  → Business logic layer
├────────────────────┤
│      Repository     │  → Database access layer
├────────────────────┤
│        Entity       │  → Domain model representation
└────────────────────┘
```

<br/>

## 🧠 Practices Adopted

- ✅ **SOLID**, **DRY**, **YAGNI**, **KISS** principles
- ✅ **REST API** design conventions
- ✅ Queries with **Spring Data JPA**
- ✅ **Dependency Injection**
- ✅ Structured **error response handling**
- ✅ Automatic **Swagger generation** with **OpenAPI 3**

<br/>

## 🗄️ Database Configuration (MySQL)

Example configuration in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskflow
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.format_sql=true
```

<br/>

## 📡 API Endpoints

### Tasks

| Method | Endpoint | Description |
|---|---|---|
| ![GET](https://img.shields.io/badge/GET-61affe?style=flat-square) | `/tasks` | Retrieve all tasks |
| ![POST](https://img.shields.io/badge/POST-49cc90?style=flat-square) | `/tasks` | Create a new task |
| ![PUT](https://img.shields.io/badge/PUT-fca130?style=flat-square) | `/tasks` | Update an existing task |
| ![DELETE](https://img.shields.io/badge/DELETE-f93e3e?style=flat-square) | `/tasks/{id}` | Delete a task |

<br/>

## 🔎 Example Requests

### ➕ Create Task

<details open>
<summary><b>Request</b></summary>

```json
{
  "title": "Study Spring Boot",
  "description": "Learn REST APIs with Spring Boot",
  "status": "PENDING",
  "priority": "HIGH",
  "deadline": "2026-07-03T15:20:02.422Z"
}
```

</details>

<details open>
<summary><b>Response</b></summary>

```json
{
  "id": 1,
  "title": "Study Spring Boot",
  "description": "Learn REST APIs with Spring Boot",
  "status": "PENDING",
  "priority": "HIGH",
  "deadline": "2026-07-03T15:20:02.422Z",
  "createdAt": "2026-07-03T15:20:02.422Z",
  "updatedAt": "2026-07-03T15:20:02.422Z"
}
```

</details>

<br/>

## 📘 Swagger Documentation

The API is fully documented using **Swagger UI**, allowing interactive exploration and testing of all endpoints.

🔗 **Swagger UI:** `http://localhost:8080/swagger-ui.html` (Available when the application is running)

<br/>

## 🚀 How to Run the Project

### 1️⃣ Clone the repository

```bash
git clone https://github.com/sarahprando/taskflow.git
```

### 2️⃣ Configure MySQL

Create the database:

```sql
CREATE DATABASE taskflow;
```

Update your credentials in `application.properties`.

### 3️⃣ Run the application

```bash
./mvnw spring-boot:run
```

### 4️⃣ Access the API

```
http://localhost:8080
```

<br/>

## 🚧 Improvements under construction

- [ ] 🔐 JWT Authentication & Authorization
- [ ] 👤 Role-based access control
- [ ] 📄 Pagination and filtering
- [ ] 🐳 Docker containerization

<br/>

## 🧩 Design Decisions

- 🏗️ **Layered architecture** to improve separation of concerns
- 🌐 **RESTful conventions** for endpoint design
- 🐬 **MySQL** as the relational persistence layer
- 📘 **Swagger** for easy API exploration and testing
- 🔢 **Enum usage** for status and priority consistency

<br/>

## 📝 Notes

> This project follows a **reusable backend template structure** and can be adapted for other systems by modifying:
> - Domain entity (`Task` → any business model)
> - Business rules (Service layer)
> - Endpoints (Controller layer)
> - Database schema

<br/>

## ✅ Final Result

A **clean, scalable, and maintainable** backend API, ready to be extended into production-level systems. 🚀

<br/>

<div align="center">

Made with ☕ and 💻

<img src="https://skillicons.dev/icons?i=java,spring,mysql" />

</div>
