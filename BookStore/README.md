# 📚 Book & Author Management API

A **Spring Boot REST API** project to manage **Books** and **Authors** with CRUD operations, pagination, and filtering.  
The project also integrates **Swagger UI** for interactive API documentation.

---

## 🚀 Features
- Manage **Books** (Create, Read, Update, Delete)
- Manage **Authors** (Create, Read, Update, Delete)
- Pagination & Filtering support
- H2 In-memory Database for quick testing
- Swagger/OpenAPI documentation
- RESTful best practices

---

## 🛠️ Tech Stack
- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database**
- **Swagger (springdoc-openapi)**

---

## 📌 Project Structure
src/main/java/com/example/demo
 ├── controller
 │    ├── BookController.java
 │    └── AuthorController.java
 ├── entity
 │    ├── Book.java
 │    └── Author.java
 ├── repository
 │    ├── BookRepository.java
 │    └── AuthorRepository.java
 ├── service
 │    ├── BookService.java
 │    └── AuthorService.java
 └── config
      └── SwaggerConfig.java

---

## ⚡ Running the Application

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-username/book-author-api.git
cd book-author-api
```

### 2️⃣ Build & Run
```bash
./mvnw spring-boot:run
```

The application will start at:  
👉 http://localhost:8080

---

## 🌐 Swagger Documentation
Once the app is running, access Swagger UI here:

👉 http://localhost:8080/swagger-ui.html

Here you can:
- Explore all available APIs
- View request/response formats
- Try out endpoints directly

---

## 📖 Example Endpoints

### 📚 Books
- `GET /api/books` → Get all books (with pagination & filters)
- `GET /api/books/{id}` → Get a single book
- `POST /api/books` → Create a new book
- `PUT /api/books/{id}` → Update an existing book
- `DELETE /api/books/{id}` → Delete a book

### ✍️ Authors
- `GET /api/authors` → Get all authors
- `GET /api/authors/{id}` → Get a single author
- `POST /api/authors` → Create a new author
- `PUT /api/authors/{id}` → Update an existing author
- `DELETE /api/authors/{id}` → Delete an author

---

## 📷 Swagger UI Screenshot (Optional)
> *(Insert a screenshot of your Swagger UI here)*

---

## 📜 License
This project is licensed under the **Apache 2.0 License**.

---

👨‍💻 Developed by **Deep Saha**
