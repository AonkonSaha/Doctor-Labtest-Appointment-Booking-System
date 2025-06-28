# 🏥 Doctor & Lab Appointment Booking System

A comprehensive full-stack **Doctor & Lab Appointment Booking System** built with **Spring Boot**, **Thymeleaf**, **JWT**, **JavaScript**, and **MySQL**. It empowers patients to securely search and book doctor or lab appointments, while administrators manage users, bookings, and system configurations via role-based access.

---

## 🚀 Key Features

### 🔐 Secure Authentication & Authorization
- JWT-based login with role-based access control: `ADMIN`, `DOCTOR`, `PATIENT`
- Secure session and password management
- Protected APIs with fine-grained permissions using `@SecurityRequirement`

### 📅 Smart Appointment Management
- Book doctor or lab test appointments with interactive modals
- View history and upcoming schedules filtered by patient
- Built-in validation for time slot conflicts and availability
- CRUD operations on bookings with consistent Swagger documentation

### 🧑‍⚕️ Doctor & Lab Directory
- Responsive card-style listings for doctors and labs
- Filter by specialization, location, and availability
- Admin features to manage doctor/lab listings with rich metadata

### 🖥️ Responsive & Modern UI
- Dynamic and user-friendly UI using **Thymeleaf** + **JavaScript**
- Sidebar-based navigation and role-specific dashboards
- Mobile-first layout with accessible modal booking system

---

## 🧱 Architecture & Best Practices

This project is designed with **clean architecture principles**, emphasizing **Domain-Driven Design (DDD)**, **SOLID**, and **DRY** to maximize scalability and code maintainability.

### ✅ Domain-Driven Design (DDD)
- Clear domain boundaries between `Doctor`, `Lab`, `Booking`, `User`, etc.
- Mapper classes to handle DTO↔Entity transformation
- Centralized validation, business logic, and service abstractions

### ✅ SOLID Principles
- **S**ingle Responsibility: Controllers, services, and mappers are modular  
- **O**pen/Closed: Easily extendable through interfaces and layered design  
- **L**iskov Substitution: Interfaces and abstract services are respected  
- **I**nterface Segregation: DTOs and mappers are specialized  
- **D**ependency Inversion: Layers interact via interfaces, not implementations  

### ✅ Don't Repeat Yourself (DRY)
- ✅ `TimeSlotValidator`: Prevents slot conflicts via reusable service  
- ✅ `ApiPaths`: Centralized class for all API endpoint paths  
- ✅ `ControllerAdvice`: Global exception handling and error formatting  
- ✅ `ValidationService`: Standard input validation for doctors/labs/appointments  
- ✅ `Mapper Classes`: Clean DTO-Entity conversion logic (e.g., `DoctorMapper`)  
- ✅ `@Parameter` + `@Operation`: Standardized Swagger annotations for all endpoints  

---

## 🛠️ Tech Stack

| Layer        | Technology                        |
|--------------|------------------------------------|
| Backend      | Spring Boot 3.2.0, Spring Security |
| Frontend     | Thymeleaf, JavaScript, CSS         |
| Database     | MySQL 8.40+                        |
| Auth         | JWT (Token-based authentication)   |
| Docs         | Swagger (via Springdoc OpenAPI 3)  |
| Build Tool   | Gradle 8.4                         |
| Design       | DDD, SOLID, DRY                    |

---
## 🧪 Dummy Data

### ✅ Login User (Dual Role - PATIENT & ADMIN)

| Name         | Contact       | Password | Role(s)         |
|--------------|---------------|----------|-----------------|
| Aonkon Saha  | 01881264859   | 12345678 | PATIENT, ADMIN  |

Password is stored as BCrypt hash: $2a$10$ulXmahQgs0lCzyjGuMOmi.vQkl9VSL1QefY8aq1OUfXANAaj4PKZe

---

### 👨‍⚕️ Doctors (Sample)

| ID | Name              | Designation   | Contact       | Hospital               | Degrees                  | Status  |
|----|-------------------|---------------|---------------|------------------------|--------------------------|---------|
| 2  | Dr. Shreya Saha   | Cardiologist  | 01881264850   | City Hospital          | MBBS(RU), MD(Cardiology) | Active  |
| 3  | Dr. Rahim Khan    | Neurologist   | 01710000000   | HealthPlus Clinic      | MBBS(DU), DM(Neurology)  | Active  |
| 4  | Dr. Susmita Sen   | Neurologist   | 91891634896   | Hall & Smith Hospital  | MBBS(DU), MD(Cardiology) | Active  |
| …  | …                 | …             | …             | …                      | …                        | …       |

---
### 🧪 Lab Tests

| ID | Test Name                        | Description                                 |
|----|----------------------------------|---------------------------------------------|
| 1  | Complete Blood Count (CBC)       | Basic blood analysis                        |
| 2  | Liver Function Test (LFT)        | Assesses liver function                     |
| 3  | Blood Sugar (Fasting/Post)       | Glucose level check                         |
| 4  | Kidney Function Test (KFT)       | Kidney indicators                           |
| 5  | Urine Routine Examination (R/E)  | Full urine analysis                         |
| …  | …                                | …                                           |

---

### 🏥 Diagnostic Centers

| ID | Center Name           | City      | Country | Contact        | Rating |
|----|-----------------------|-----------|---------|----------------|--------|
| 1  | HealthFirst Diagnostics | New York  | USA     | +1-212-555-0101 | 4.5    |
| 2  | MediCare Labs         | Los Angeles | USA    | +1-310-555-0202 | 4.2    |
| 3  | CityLab Diagnostics   | London     | UK      | +44-20-7946-0303| 4.7    |
| …  | …                     | …         | …       | …              | …      |

---

## ⚙️ Getting Started

### ✅ Prerequisites
- Java 21+
- Gradle 8.4+
- MySQL 8.40+
- IDE: IntelliJ (recommended)

---

## 📘 Swagger API Documentation

Auto-generated documentation is available via:

\`\`\`
http://localhost:8080/swagger-ui.html
\`\`\`

All endpoints include:
- ✨ `@Operation` summaries
- 🧾 `@ApiResponse` and `@Schema`-based response structures
- 🔐 `@SecurityRequirement` for secured routes
- 🧩 `@Parameter` descriptions for `@PathVariable` inputs

---

## 📈 Status

✅ **Controller and DTO Swagger annotations complete**  
✅ **Booking modules (doctor & lab) fully functional**  
🛠️ **Profile editing, notifications & pagination in progress**

---
> ✅ **Note:** You can login using the following credentials:  
> - **Mobile:** `01881264859`  
> - **Password:** `12345678`  
> - **Roles:** `PATIENT`, `ADMIN`


