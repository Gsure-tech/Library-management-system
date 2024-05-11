# Library Management System API

## Project Description

This project is a Library Management System API developed using Spring Boot. It allows librarians to manage books, patrons, and borrowing records efficiently. The API supports various operations through RESTful endpoints.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- An SQL database (MySQL, PostgreSQL, or H2 for testing purposes)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Gsure-tech/Library-management-system.git
   cd library-management-system
2. **Set up the database:**
    Ensure your SQL database is running.
    Update the application.properties or application.yml with the database connection details.
3. **Build the application:**
     mvn clean install
4. **Run the application:**
  java -jar target/library-management-system-0.0.1-SNAPSHOT.jar
  - Alternatively, you can run it directly using Spring Boot Maven plugin:

mvn spring-boot:run
###  API Endpoints
  Book Management
  - GET /api/books - Retrieve a list of all books.
  - GET /api/books/{id} - Retrieve details of a specific book by ID.
  - POST /api/books - Add a new book to the library.
  - PUT /api/books/{id} - Update an existing book's information.
  - DELETE /api/books/{id} - Remove a book from the library.
  - Patron Management
  - GET /api/patrons - Retrieve a list of all patrons.
  - GET /api/patrons/{id} - Retrieve details of a specific patron by ID.
  - POST /api/patrons - Add a new patron to the system.
  - PUT /api/patrons/{id} - Update an existing patron's information.
  - DELETE /api/patrons/{id} - Remove a patron from the system.
  - Borrowing Management
  - POST /api/borrow/{bookId}/patron/{patronId} - Allow a patron to borrow a book.
  - PUT /api/return/{bookId}/patron/{patronId} - Record the return of a borrowed book.


### Authentication
  This API uses JWT-based authentication. To interact with the API, follow these steps:

  Login to obtain a token:


curl -X POST -H "Content-Type: application/json" -d '{"username":"your_username", "password":"your_password"}' http://localhost:8080/api/login
Use the token in subsequent requests:

curl -X GET http://localhost:8080/api/books -H "Authorization: Bearer your_token_here"
###  Testing
   Unit tests have been written using JUnit and Mockito. To run the tests:
mvn test



