# Tokimon Info Manager

## Overview
**Tokimon Info Manager** is a Java-based client-server application developed as part of **CMPT 213: Object-Oriented Design in Java (SFU, June 2024)**.  
The application allows users to **manage Tokimon data** through a user-friendly JavaFX client, while the server handles data storage, retrieval, and updates via a RESTful API.

---

## Features
- Full **CRUD operations** for Tokimon entries: add, delete, edit, view, and debug.
- **JavaFX client interface** for seamless user interaction.
- **Spring Boot server** exposing a RESTful API.
- **JSON-based storage** to reliably persist Tokimon data.
- Modular and maintainable code structure demonstrating object-oriented design principles.

---

## How It Works
1. **Server:**  
   - Handles all requests for Tokimon data via REST endpoints (`/add`, `/delete`, `/edit`, `/view`, etc.).
   - Stores data in a JSON format for persistence.
2. **Client:**  
   - JavaFX application that communicates with the server.
   - Displays Tokimon data and allows users to perform actions like adding or editing entries.
3. **Communication:**  
   - HTTP requests and responses are used to interact between the client and server.
   - JSON is used for data exchange.

---

## Installation
1. Clone the repository:  
   ```bash
   https://github.com/AyushArora10hg/TokimonInfoManager---CMPT213.git
2. Open the project in your IDE (IntelliJ, Eclipse, etc.).
3. Build the server with Maven:
   ```bash
   mvn clean install
4. Run the Spring Boot server:
   ```bash
   mvn spring-boot:run
5. Build and run the JavaFX client:
   ```bash
   mvn javafx:run

---

## Dependencies
This project uses the following libraries and tools:

- **Java 22 or higher** – Core language and runtime.
- **JavaFX 22** – GUI framework.
  - `javafx-controls`
- **JSON-Simple 1.1.1** – For reading and parsing JSON files.
- **JUnit 5** – For running unit tests (optional).

Dependencies are managed via Maven (`pom.xml`).

---

## License

This project is a **class project** developed for the CMPT213 course (Summer 2024) at Simon Fraser University.  

- The project is intended for **educational purposes only**.  
- It **cannot be used for commercial purposes**.

---

## Author

- **Ayush Arora** – CMPT 213, Summer 2024
- **Hetmay Ketan Vora** – CMPT 213, Summer 2024


