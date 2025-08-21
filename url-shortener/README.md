# 📌 URL Shortener API

A simple yet powerful **Spring Boot application** that allows you to shorten long URLs, redirect users, and track click statistics.  
It includes **Swagger documentation**, **CORS support**, and supports **custom short codes** with optional expiry.

---

## ✨ Features
- 🔗 Shorten any long URL
- 🏷️ Support for **custom aliases** (e.g., `/my-link`)
- ⏳ Set optional **expiry time** for short links
- 📊 Track and fetch **click counts**
- 🌐 **Swagger UI** for API documentation
- 🔒 CORS enabled (ready for frontend integration)
- ⚡ Fast and lightweight – built with **Spring Boot**

---

## 🛠️ Tech Stack
- **Java 17+**
- **Spring Boot 3**
- **Spring Web**
- **SpringDoc OpenAPI (Swagger)**
- **Lombok**
- *(Optional: H2 / MySQL / MongoDB if persistence is added later)*

---

## 🚀 Getting Started


```bash
git clone https://github.com/Deep-Saha1925/INTERNSHIP_PROJECTS_ELEVATE_LABS.git
cd url-shortener

mvn spring-boot:run
The service will start at 👉 http://localhost:8080

Swagger API Docs

Once the app is running, open:
👉 Swagger UI: http://localhost:8080/swagger-ui.html

👉 OpenAPI Docs: http://localhost:8080/v3/api-docs

Configuration

Default CORS allows:

http://localhost:3000 (React)

http://localhost:4200 (Angular)

You can modify allowed origins in UrlShortenerApplication.java under corsConfigurer().

Future Improvements

✅ Persistent storage (H2, MySQL, MongoDB)

✅ Expiry mechanism (auto-delete expired URLs)

✅ Analytics (geolocation, device, browser stats)

✅ Authentication & User accounts

👨‍💻 Author

Deep Saha
📧 dip23447@gmail.com

🌐 GitHub
 | LinkedIn
```

