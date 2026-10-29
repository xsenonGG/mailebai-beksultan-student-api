# Mailebai Beksultan Student API — DevOps Midterm Documentation

Student: Mailebai Beksultan
Group: IT2-2313
ID: 37691

## Implementation by Mailebai Beksultan
Student API uses Java 21, Spring Boot, Gradle and PostgreSQL.

Base path: /api/mailebai-beksultan/students
- POST: create a student with validated name, email and group.
- GET: retrieve all students.
- GET /{id}: retrieve one student; return 404 if missing.

Database settings are supplied through DB_URL, DB_USER and DB_PASSWORD.
The application listens on port 8081; Jenkins uses port 8080.

## CRUD completion by Mailebai Beksultan
- PUT /{id}: update an existing student.
- DELETE /{id}: delete an existing student; return 204.
- Missing records return 404 for retrieval, update and deletion.
- Automated tests check CRUD persistence and missing-record behavior.
- Tests use an isolated H2 database; production uses PostgreSQL.
- Run tests: ./gradlew test

## Docker setup
Application version: 1.0.1-37691.
Student details: GET /api/mailebai-beksultan/info.

1. Create .env using .env.example and set your own database password.
2. Run: docker compose up -d --build
3. API: http://127.0.0.1:8081/api/mailebai-beksultan/students
4. Stop containers while retaining data: docker compose down

PostgreSQL is reached through service name db.
A named volume stores database data outside the containers.
Never commit .env or real passwords: Git history retains secrets,
and publishing them can expose the database or other accounts.

## CI verification
Mailebai Beksultan, IT2-2313, ID 37691.
Jenkins polls Git every minute and runs checkout, build, tests,
Docker build and image verification when a new commit appears.
