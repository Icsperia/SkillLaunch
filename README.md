SkillLaunch Backend API

The SkillLaunch backend is a RESTful API developed to help students find jobs, internships, and practice opportunities more easily. It serves as the bridge between educational institutions and the corporate world, allowing companies to post opportunities and students to apply or search for them.

TECH STACK

    Java: 21

    Framework: Spring Boot 3.4.4

    Security: Spring Security & JWT (JSON Web Tokens)

    Database: MySQL

    Build Tool: Gradle

    Containerization: Docker & Docker Compose (for local database setup)

CORE FEATURES

    Dual-User Authentication System:

        Independent registration and login endpoints for Students and Companies.

        Secure JWT-based session management.

    Profile Management:

        Students: Manage personal details, educational background, skills, and experience.

        Companies: Manage company descriptions, field of activity, and reviews.

    Offer Management:

        Companies can seamlessly create, update, and delete job or internship offers.

    Offer Filtering & Search:

        Advanced filtering options allowing students to search for offers based on type and location.

    Email Notifications:

        Integrated JavaMailSender for sending emails (e.g., OTPs, forgotten passwords).

DATABASE ARCHITECTURE
The application uses a MySQL relational database to handle highly structured data and entity relationships (e.g., mapping an Opportunity to a specific Companie, storing user credentials, and managing secure tokens).

GETTING STARTED

Prerequisites

    Java 21 installed on your machine.

    Docker Desktop (optional, but recommended for spinning up MySQL).

    A local or remote MySQL server instance.

    Clone the repository

git clone https://github.com/yourusername/skilllaunch.git
cd skilllaunch

    Configure the Database
    The project includes a compose.yml file to quickly spin up a MySQL database via Docker.

docker-compose up -d

Note: This will expose a MySQL instance on port 3306 with the database name practica.

    Environment Variables & Properties
    Navigate to src/main/resources/application.properties and ensure your database and email credentials match your environment:

spring.datasource.url=jdbc:mysql://127.0.0.1:3306/practica
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.protocol=smtp
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

    Build and Run
    Use the included Gradle wrapper to build and start the Spring Boot application:

Linux/macOS:
./gradlew build
./gradlew bootRun

Windows:
gradlew.bat build
gradlew.bat bootRun

API ENDPOINTS OVERVIEW
Here are some of the primary REST endpoints exposed by the application:

Authentication

    POST /api/auth/register/student - Register a new student

    POST /api/auth/login/student - Authenticate student and receive JWT

    POST /api/auth/register/companie - Register a new company

    POST /api/auth/login/companie - Authenticate company and receive JWT

Student

    GET /api/student/all - Fetch all students

    GET /api/student/{id} - Fetch specific student profile details

    PUT /api/student/studentUpdate/{id} - Update a student's profile (skills, experience, etc.)

Company

    GET /api/companie/{id} - Fetch company profile details

    PUT /api/companie/companieUpdate/{id} - Update company details

Opportunities (Offers)

    POST /api/offers/postOffers/{companieId} - Post a new job/internship offer

    GET /api/offers/filters/findAll - Retrieve all available offers

    GET /api/offers/filters/findByType/{type} - Filter offers by type (e.g., Internship, Full-time)

    GET /api/offers/filters/findByLocation/{location} - Filter offers by city/location

    PUT /api/offers/updateOffers/{companieId}/{offersId} - Update an existing offer

    DELETE /api/offers/delete/{offersId}/{companieId} - Delete an offer
