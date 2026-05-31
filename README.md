# Student Records REST API

SECJ4383 Software Construction - Assignment 2 (Topic 7: REST API with Spring Boot)

A simple Student Records management system built with Spring Boot. It manages student
information (name, matric number, program, email, CGPA) and exposes the operations as
RESTful web-services that return JSON.

## Group: TECH TITANS

| Name | Matric No |
| --- | --- |
| Mohammad Areeb | A22EC4035 |
| Samin Sarwat | A22EC4040 |
| Someyo Kamal Utsho | A22EC9007 |
| Mariam Hanif | A22EC4034 |

## Tech Stack

- Java 21
- Spring Boot 3.3.5 (Spring Web, Spring Data JPA, Bean Validation)
- H2 in-memory database
- Maven (Maven Wrapper included, so Maven does not need to be installed)

## The Five Functionalities

| # | Functionality | Method & Endpoint |
| --- | --- | --- |
| 1 | Register a new student | `POST /api/students` |
| 2 | List all students / get one by ID | `GET /api/students`, `GET /api/students/{id}` |
| 3 | Update an existing student | `PUT /api/students/{id}` |
| 4 | Delete a student | `DELETE /api/students/{id}` |
| 5 | Search students by name or program | `GET /api/students/search?name=...&program=...` |

All five functionalities are implemented and exposed as REST endpoints, which covers the
requirement to expose at least two functionalities as RESTful web-services.

## Project Structure

```
src/main/java/com/techtitans/studentrecords
├── StudentRecordsApplication.java   # application entry point
├── DataLoader.java                  # seeds a few demo records on startup
├── controller
│   └── StudentController.java       # REST endpoints
├── service
│   └── StudentService.java          # business logic (the 5 functionalities)
├── repository
│   └── StudentRepository.java       # Spring Data JPA repository
├── model
│   └── Student.java                 # JPA entity + validation rules
└── exception
    ├── StudentNotFoundException.java
    ├── DuplicateMatricException.java
    └── ApiExceptionHandler.java     # returns clean JSON error responses
```

## How to Run

From the project folder:

```bash
# macOS / Linux
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

The application starts at `http://localhost:8080`.

To run the tests:

```bash
./mvnw test
```

### H2 Console (optional)

While the app is running, open `http://localhost:8080/h2-console` and connect with:

- JDBC URL: `jdbc:h2:mem:studentdb`
- User: `sa`
- Password: (leave blank)

## Student JSON Format

```json
{
  "name": "Aisha Khan",
  "matricNo": "A22EC1234",
  "program": "Computer Science",
  "email": "aisha@graduate.utm.my",
  "cgpa": 3.7
}
```

Validation rules:

- `name`, `matricNo`, `program`, `email` are required
- `email` must be a valid email address
- `cgpa` must be between 0.0 and 4.0
- `matricNo` must be unique

## Testing with POSTMAN

Set the request body to **raw / JSON** for POST and PUT requests.

1. **Create** - `POST http://localhost:8080/api/students`
   ```json
   { "name": "Aisha Khan", "matricNo": "A22EC1234", "program": "Computer Science", "email": "aisha@graduate.utm.my", "cgpa": 3.7 }
   ```
   Returns `201 Created` with the saved student.

2. **List all** - `GET http://localhost:8080/api/students`

3. **Get by ID** - `GET http://localhost:8080/api/students/1`

4. **Update** - `PUT http://localhost:8080/api/students/1`
   ```json
   { "name": "Mohammad Areeb", "matricNo": "A22EC4035", "program": "Cyber Security", "email": "areeb@graduate.utm.my", "cgpa": 3.8 }
   ```

5. **Delete** - `DELETE http://localhost:8080/api/students/1` (returns `204 No Content`)

6. **Search** - `GET http://localhost:8080/api/students/search?program=Software`
   or `GET http://localhost:8080/api/students/search?name=areeb`

A ready-to-import Postman collection is included: `StudentRecords.postman_collection.json`.

## Example Error Responses

Not found (`404`):

```json
{ "status": 404, "error": "Not Found", "message": "No student found with id 999" }
```

Validation failed (`400`):

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": { "email": "Email must be valid", "cgpa": "CGPA cannot be above 4.0" }
}
```

Duplicate matric number (`409`):

```json
{ "status": 409, "error": "Conflict", "message": "A student with matric number A22EC4035 already exists" }
```
