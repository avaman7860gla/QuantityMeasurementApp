# 📏 Quantity Measurement App (QMA)

## 📌 Overview
The **Quantity Measurement App (QMA)** is a robust and scalable Java-based system designed to handle different types of physical quantities such as **Length, Weight, Volume, and Temperature**.

The application is developed incrementally using **Use Case (UC) driven design**, where each UC introduces new functionality, improves architecture, and applies advanced software engineering principles.

---

## 🚀 Key Features
- ✅ Measurement equality comparison  
- ✅ Multi-unit support (Feet, Inch, Kg, Litre, etc.)  
- ✅ Unit-to-unit conversion  
- ✅ Arithmetic operations (Add, Subtract, Divide)  
- ✅ Generic and reusable quantity model  
- ✅ Multi-category measurement system  
- ✅ Backend with Spring Boot  
- ✅ Database integration using JDBC  
- ✅ Authentication with JWT & OAuth2  

---

# 📚 UC-Wise Detailed Functionality

---

## 🔹 UC1: Feet Measurement Equality

### 📌 Functionality
- Compare two **length measurements (in feet)** for equality.
- Handles:
  - Null values
  - Type mismatches
  - Floating-point precision issues

### 🧠 Concepts
- Object Equality
- Floating-point Comparison
- Unit Testing

---

## 🔹 UC2: Feet and Inches Measurement Equality

### 📌 Functionality
- Supports **mixed unit comparison (Feet & Inches)**.
- Converts inches into feet internally for accurate comparison.

### 🧠 Concepts
- Encapsulation
- Internal conversion handling

---

## 🔹 UC3: Generic Quantity Class

### 📌 Functionality
- Introduces a **generic Quantity class**.
- Eliminates duplicate logic for different units.
- Supports multiple unit types using a unified structure.

### 🧠 Concepts
- DRY Principle
- Polymorphism
- ENUM for units
- Abstraction

---

## 🔹 UC4: Extended Unit Support

### 📌 Functionality
- Adds support for more units (e.g., cm, meter).
- Ensures **backward compatibility** with existing logic.

### 🧠 Concepts
- Scalability
- ENUM extensibility
- Validation

---

## 🔹 UC5: Unit-to-Unit Conversion

### 📌 Functionality
- Convert values between units (e.g., feet → inches).
- Uses conversion factors defined in ENUM.

### 🧠 Concepts
- Immutability
- Value Object Design
- Method Overloading

---

## 🔹 UC6: Addition of Two Length Units

### 📌 Functionality
- Add two measurements with different units.
- Internally:
  - Converts both to a **base unit**
  - Performs addition
  - Returns result

### 🧠 Concepts
- Arithmetic on Value Objects
- Normalization
- Precision Handling

---

## 🔹 UC7: Addition with Target Unit

### 📌 Functionality
- Add two quantities and return result in a **desired unit**.
- Example: (1 ft + 12 in → result in inches)

### 🧠 Concepts
- Method Overloading
- API Flexibility
- Unit Independence

---

## 🔹 UC8: Refactoring Unit Enum

### 📌 Functionality
- Moves unit logic into a **standalone enum class**.
- Separates conversion logic from business logic.

### 🧠 Concepts
- SRP (Single Responsibility Principle)
- Separation of Concerns
- Delegation Pattern

---

## 🔹 UC9: Weight Measurement

### 📌 Functionality
- Introduces **Weight category (kg, gram)**.
- Supports:
  - Equality
  - Conversion
  - Arithmetic

### 🧠 Concepts
- Multi-category design
- Base unit normalization
- HashCode & Equals

---

## 🔹 UC10: Generic Quantity with Unit Interface

### 📌 Functionality
- Introduces **IMeasurable interface**.
- Enables handling multiple measurement categories generically.

### 🧠 Concepts
- Generics
- Interface-based design
- LSP & OCP

---

## 🔹 UC11: Volume Measurement

### 📌 Functionality
- Adds **Volume category (Litre, ml, Gallon)**.
- Fully supports:
  - Equality
  - Conversion
  - Addition

### 🧠 Concepts
- Generic scalability
- Conversion precision
- Polymorphism

---

## 🔹 UC12: Subtraction & Division Operations

### 📌 Functionality
- Adds:
  - Subtraction of quantities
  - Division operations
- Handles edge cases like division by zero.

### 🧠 Concepts
- Non-commutative operations
- Error handling
- Precision control

---

## 🔹 UC13: Centralized Arithmetic Logic

### 📌 Functionality
- Centralizes all arithmetic operations.
- Uses a **single logic source** for operations like add/subtract.

### 🧠 Concepts
- DRY enforcement
- Lambda expressions
- Functional interfaces

---

## 🔹 UC14: Temperature Measurement

### 📌 Functionality
- Adds **Temperature category (Celsius, Fahrenheit)**.
- Handles:
  - Non-linear conversions
  - Restricted operations (not all arithmetic valid)

### 🧠 Concepts
- ISP (Interface Segregation Principle)
- Non-linear conversion handling
- Exception handling

---

## 🔹 UC15: N-Tier Architecture Refactoring

### 📌 Functionality
- Refactors project into layers:
  - Controller
  - Service
  - Repository

### 🧠 Concepts
- N-Tier Architecture
- DTOs
- Dependency Injection
- SOLID Principles

---

## 🔹 UC16: Database Integration (JDBC)

### 📌 Functionality
- Stores measurement data in database.
- Implements CRUD operations using JDBC.

### 🧠 Concepts
- JDBC
- SQL queries
- Connection management

---

## 🔹 UC17: Spring Backend

### 📌 Functionality
- Converts application into **Spring Boot backend**.
- Provides REST APIs for operations.

### 🧠 Concepts
- Spring MVC
- REST APIs
- Dependency Injection
- JPA

---

## 🔹 UC18: Authentication & User Management

### 📌 Functionality
- Adds user authentication.
- Supports:
  - JWT-based login
  - Google OAuth2 login

### 🧠 Concepts
- Spring Security
- JWT
- OAuth2

---

## 🔹 UC19 & UC20: Frontend Development & Integration (In another repository)

### 📌 Functionality
- Builds UI for measurement input and comparison  
- Integrates frontend with backend APIs  
- Displays dynamic results to users  

### 🧠 Concepts
- HTML, CSS, JavaScript  
- REST API Integration  
- Fetch API / JSON Handling

---

## 🔹 UC21: Microservices Integration with QMA App

### 📌 Functionality
This module converts the QMA application into a **Microservices Architecture** for better scalability and modularity.

Services included:
- **API Gateway** – Single entry point, handles routing and security  
- **Auth Service** – Manages authentication & JWT validation  
- **Measurement Service** – Handles measurement-related operations  
- **History Service** – Stores and retrieves measurement history  

### 🧠 Concepts
- Microservices Architecture  
- API Gateway Routing  
- RESTful Communication  
- Service-to-Service Interaction  

---

# 🛠️ Tech Stack
- **Language:** Java  
- **Framework:** Spring Boot, Spring Cloud  
- **Database:** JDBC / MySQL  
- **Architecture:** Microservices + N-Tier  
- **Build Tool:** Maven  
- **Security:** Spring Security, JWT, OAuth2  

---

# 📈 Learning Outcomes
- Strong understanding of **OOP & Design Principles**
- Hands-on experience with **Generics & Functional Programming**
- Knowledge of **Backend Development (Spring Boot & REST APIs)**
- Implementation of **Microservices Architecture**
- API Gateway & **Service-to-Service Communication**
- Secure systems using **JWT & OAuth2**
- Real-world handling of **Measurement Systems**

---

# 📌 Conclusion
The **Quantity Measurement App (QMA)** demonstrates a complete journey from **basic measurement comparison** to a **scalable microservices-based system** with authentication, distributed services, and database integration.

It serves as an excellent project to master:
- Clean Code Practices  
- Software Architecture (Monolithic → Microservices)  
- Secure Backend Development  
- Real-world Problem Solving

---
