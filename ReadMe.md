# 🏥 Doctor & Lab Appointment Booking System

A full-stack **Doctor & Lab Appointment Booking System** built with **Spring Boot**, **Thymeleaf**, **JWT**, **JavaScript**, and **MySQL**. The application enables patients to search and book appointments with doctors and labs, while administrators can manage users, appointments, and services through a secure and role-based system.

---

## 🚀 Key Features

### 🔐 Authentication & Authorization
- Custom login and registration flow using JWT
- Role-based access control (Admin, Doctor, Patient)
- Secure session management

### 📅 Appointment Booking
- Book appointments with doctors or labs via interactive modals
- View appointment history and upcoming schedules
- Real-time availability management

### 🧑‍⚕️ Doctor & Lab Directory
- Search and filter doctors by specialty or location
- Responsive doctor/lab cards with booking options
- Admin control to create, update, and delete listings

### 🖥️ Responsive UI
- Clean, modern user interface using Thymeleaf and JavaScript
- Sidebar navigation for all user types
- Mobile-friendly design with dynamic layout and modals

---

## 🧱 Architecture & Best Practices

This project follows **Domain-Driven Design (DDD)**, **Don't-Repeat YOurself (DRY)** and adheres to **SOLID Principles** to ensure scalability, maintainability, and clean separation of concerns.
### ✅ Domain-Driven Design (DDD)
- Separation between business logic, application flow, and infrastructure
- Each domain entity models real-world concepts (e.g., Doctor, Appointment)

### ✅ SOLID Principles
- **S**ingle Responsibility: Each class or component has one responsibility  
- **O**pen/Closed: System is open for extension, closed for modification  
- **L**iskov Substitution: Replace base classes with subclasses without issues  
- **I**nterface Segregation: Fine-grained interfaces for clear contracts  
- **D**ependency Inversion: Depends on abstractions, not concrete classes

### ✅ Don't Repeat Yourself (DRY)
- Instead of duplicating time slot checks in multiple controllers or services, create a reusable TimeSlotValidator service to  validate overlapping appointments or availability
- Use centralized mapper classes (e.g., AppointmentMapper, DoctorMapper) to convert between entities and DTOs, avoiding repeated mapping logic across controllers
- Store all endpoint paths in a centralized ApiPaths class to prevent hardcoding URLs in multiple places
- Handle repeated error cases (e.g., not found, invalid input) using a global @ControllerAdvice instead of repeating try-catch blocks
- Move input validation (e.g., for doctor registration, appointment booking) into a reusable ValidationService instead of embedding it directly in controllers
- Centralize JWT extraction and role verification logic in a utility class or filter, rather than repeating it in each secured endpoint
- Use a standard response wrapper (e.g., ApiResponse<T>) for success/error responses across all controllers to avoid redundant formatting
---

## 🛠️ Tech Stack

| Layer             | Technology                        |
|------------------|------------------------------------|
| Backend           | Spring Boot, Spring Security, JWT |
| Frontend          | Thymeleaf, HTML, CSS, JavaScript  |
| Database          | MySQL                             |
| Authentication    | JWT (JSON Web Tokens)             |
| Architecture      | Domain-Driven Design, SOLID , DRY |

---

## ⚙️ Getting Started

### ✅ Prerequisites
- Java 21+
- Spring Boot Version 3.2.0
- Gradle 8.4+
- MySQL 8.40+
- IDE IntelliJ 



