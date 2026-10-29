# Mailebai Beksultan Student API

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
