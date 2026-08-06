# 🚀 SkillLaunch — Backend API

A RESTful backend that connects **students** with **companies** offering jobs, internships, and practice placements — built with **Spring Boot 3** and **Java 21**. Companies post opportunities, students search and filter them, and both sides authenticate independently through a shared JWT-based security layer.

---

## Overview

SkillLaunch models two distinct account types — `Student` and `Companie` (company) — each with their own registration, login, and profile-update flow, but sharing one `Opportunities` domain that links postings back to the company that created them. Access control is stateless: every request after login carries a signed JWT, verified on each call with no server-side session.

## Core Features

- **Dual-user authentication** — independent register/login endpoints for students and companies, each backed by their own Spring Security `UserDetails` implementation.
- **JWT session management** — stateless, HMAC-signed (HS256) access tokens issued on login/registration and validated on every request via a custom `OncePerRequestFilter`.
- **Role-based domain model** — a shared `Role` enum (`STUDENT`, `ADMIN`, `COMPANIE`) feeds Spring Security authorities, with the underlying route-level restrictions scaffolded in and ready to be turned on (see [Known Limitations](#known-limitations--roadmap)).
- **Profile management** — students maintain faculty/university, skills, experience and job-title fields; companies maintain description, field of activity, and reviews.
- **Opportunity lifecycle** — companies create, update, and delete postings; each posting is linked to its owning company via a JPA `@ManyToOne` relationship.
- **Search & filtering** — students can query opportunities by type or by location.
- **Email notifications** — `JavaMailSender`-based service for transactional email (e.g. OTP / forgotten-password flows), with a `ForgotPassword` entity for OTP + expiry tracking.
- **Polyglot persistence** — MySQL (via JPA/Hibernate) for the core relational domain, with a MongoDB document (`CV`) scaffolded for future résumé storage.
- **UUIDv7 support** — a custom Hibernate `SequenceStyleGenerator` for time-ordered UUID primary keys, available as an alternative to the default identity strategy.

## Architecture

<img width="1622" height="4507" alt="MAX10 Clock Configuration-2026-08-06-144521" src="https://github.com/user-attachments/assets/f31b1e11-b1c9-46c2-944f-1fc62be1b575" />

**Auth flow:** on register/login, the relevant `AuthentificationService*` builds/validates the account, encodes the password with **BCrypt**, and issues a JWT via `JwtService`. On every subsequent request, `JwtAuthentificationFilter` extracts the bearer token, resolves the subject email through `ComposedUserDetails`, and — depending on which entity that email belongs to — authenticates the request as either a `Student` or a `Companie` principal.

## Domain Model

| Entity | Purpose |
|---|---|
| `Student` | Student account — identity, faculty/university, skills, experience; implements `UserDetails` |
| `Companie` | Company account — identity, description, field of activity, reviews; implements `UserDetails` |
| `Opportunities` | A job/internship posting — type, description, location, skills, status, expiry date; owned by a `Companie` |
| `Role` | Shared enum: `STUDENT`, `ADMIN`, `COMPANIE` |
| `TokenStudent` / `TokenCompanie` | Per-account token records issued alongside the JWT |
| `ForgotPassword` | OTP + expiry, linked to a `Student`, for password-reset flows |
| `CV` | MongoDB document intended for résumé storage (scaffolded) |

## API Endpoints

### Authentication — `/api/auth`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/register/student` | Register a new student account |
| `POST` | `/login/student` | Authenticate a student, receive a JWT |
| `POST` | `/register/companie` | Register a new company account |
| `POST` | `/login/companie` | Authenticate a company, receive a JWT |

### Students — `/api/student`
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/all` | List all students |
| `GET` | `/{id}` | Fetch a student by ID (raw entity) |
| `GET` | `/studentDto/{id}` | Fetch a student profile as a DTO |
| `PUT` | `/studentUpdate/{id}` | Update a student's profile (skills, experience, etc.) |

### Companies — `/api/companie`
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/{id}` | Fetch a company profile |
| `GET` | `/companieDto/{id}` | Fetch a company profile (DTO variant) |
| `PUT` | `/companieUpdate/{id}` | Update company details |

### Opportunities — `/api/offers`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/postOffers/{companieId}` | Create a new opportunity for a company |
| `PUT` | `/updateOffers/{companieId}/{offersId}` | Update an existing opportunity |
| `DELETE` | `/delete/{offersId}/{companieId}` | Delete an opportunity |

### Filtering & Search — `/offers/filters`
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/findAll` | Retrieve all opportunities |
| `GET` | `/findByType/{type}` | Filter opportunities by type |
| `GET` | `/findByLocation/{location}` | Filter opportunities by location |

> Note: the filtering controller is mapped at `/offers/filters`, not under `/api`, unlike the rest of the API.

## Technical Stack

| | |
|---|---|
| **Language** | Java 21 |
| **Framework** | Spring Boot 3.4.4 |
| **Security** | Spring Security, JWT (`jjwt`), BCrypt password hashing |
| **Persistence** | Spring Data JPA + MySQL (relational core), Spring Data MongoDB (CV documents) |
| **Validation** | Jakarta Bean Validation (Hibernate Validator) |
| **Email** | Spring Boot Starter Mail (`JavaMailSender`) |
| **Build tool** | Gradle (wrapper included) |
| **Containerization** | Docker Compose (local MySQL) |
| **Testing** | JUnit 5, Spring Security Test, H2 (in-memory DB for tests) |

## Getting Started

### Prerequisites
- Java 21
- Docker (optional, for a local MySQL instance)
- A MySQL server (local or containerized) and, if you want the CV feature to work, a MongoDB instance

### 1. Clone and configure
```bash
git clone https://github.com/<your-username>/SkillLaunch.git
cd SkillLaunch
```

### 2. Start MySQL via Docker Compose
```bash
docker-compose up -d
```
This exposes MySQL on port `3306` with database `practica`.

### 3. Configure application properties
Set the following in `src/main/resources/application.properties` (or, better, externalize them as **environment variables** — see the security note below):

```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/practica
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

spring.data.mongodb.uri=${MONGODB_URI}
spring.data.mongodb.database=practica

spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_APP_PASSWORD}
```

### 4. Build and run
```bash
# Linux/macOS
./gradlew build
./gradlew bootRun

# Windows
gradlew.bat build
gradlew.bat bootRun
```

The API will be available at `http://localhost:8080`.

## ⚠️ Security Note

Before making this repository public (or if it already is), rotate and remove any credentials currently hard-coded in `application.properties` — MySQL, MongoDB, and email credentials should never be committed to source control. Move them to environment variables or a secrets manager, and add `application.properties` (or a `application-local.properties` variant) to `.gitignore`, keeping only a template (`application.properties.example`) with placeholder values in the repo.

## Known Limitations / Roadmap

- **Role-based authorization is scaffolded but not enforced** — `SecurityConfig` currently `permitAll()`s the student/company/offers routes, with `@PreAuthorize`/`hasRole` checks present in the controllers but commented out. Enabling these is the natural next step toward least-privilege access.
- **CORS is disabled outright** — fine for local development, but will need a proper CORS policy before a separate frontend can call this API from a browser.
- **CV storage (MongoDB)** is modeled but not yet wired into a repository or controller.
- **Consistent DTO naming** — endpoints mix raw entity responses (`GET /api/student/{id}`) with DTO responses (`GET /api/student/studentDto/{id}`); consolidating on DTOs everywhere would tighten the API surface.
- **RSA keypair** present under `resources/jwt/` (`app.key` / `app.pub`) but the current `JwtService` implementation signs with a symmetric HMAC key rather than these — likely leftover from an earlier design, worth removing or wiring in for asymmetric signing.

---

*A full-stack-ready Spring Boot backend demonstrating dual-role JWT authentication, layered service/repository architecture, and mixed relational + document persistence.*
