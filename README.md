# 🏥 hospital-api

A Spring Boot REST API for managing hospital operations, developed as part of a university coursework project. The system handles users, doctors, appointments, and medical reports with full CRUD functionality and secure access.

---

## 📂 Project Structure

```
hospital-api/
└── hospital-coursework/
├── .gitignore
├── mvnw / mvnw.cmd
├── pom.xml
├── .mvn/
│ └── wrapper/
│ ├── maven-wrapper.jar
│ └── maven-wrapper.properties
└── src/
├── main/
│ ├── java/
│ │ └── com/
│ │ └── brovko/
│ │ └── hospitalcoursework/
│ │ ├── HospitalCourseworkApplication.java
│ │ ├── configuration/
│ │ │ └── security/
│ │ │ ├── AuthenticationFacade.java
│ │ │ ├── IAuthenticationFacade.java
│ │ │ ├── MyUserDetails.java
│ │ │ └── SecurityConfig.java
│ │ ├── controller/
│ │ │ ├── AppointmentController.java
│ │ │ ├── DocInfoController.java
│ │ │ ├── ExceptionHandlerController.java
│ │ │ ├── MedicalReportController.java
│ │ │ └── UserController.java
│ │ ├── exception/
│ │ │ ├── LogNotFoundException.java
│ │ │ └── RoleException.java
│ │ ├── model/
│ │ │ ├── AppointmentPOJO.java
│ │ │ ├── DocInfoPOJO.java
│ │ │ ├── MedicalReportPOJO.java
│ │ │ ├── RoleNamePOJO.java
│ │ │ ├── UserPOJO.java
│ │ │ ├── UserRolePOJO.java
│ │ │ └── UserView.java
│ │ ├── repository/
│ │ │ ├── AppointmentRepository.java
│ │ │ ├── DocInfoRepository.java
│ │ │ ├── MedicalReportRepository.java
│ │ │ ├── RoleNameRepository.java
│ │ │ ├── UserRepository.java
│ │ │ └── UserRoleRepository.java
│ │ └── service/
│ │ ├── AppointmentService.java
│ │ ├── DocInfoService.java
│ │ ├── MedicalReportService.java
│ │ ├── UserDetailsServiceImpl.java
│ │ └── UserService.java
│ └── resources/
│ └── application.properties
└── test/
└── java/
```

---

## 🚀 Features

- Modular clean architecture
- Full REST API for users, doctors, appointments, and medical reports
- Custom Spring Security configuration and user authentication
- DTOs and POJOs for clean model-view separation
- Exception handling and input validation

---

## 🔐 Security

Custom Spring Security setup:
- `SecurityConfig` — defines rules, encoders, access restrictions
- `MyUserDetails` — bridges domain model with Spring Security
- `AuthenticationFacade` — gets the current logged-in user
- `UserDetailsServiceImpl` — loads credentials from the database

---

### 👤 Users & Doctors
- `GET    /users`
- `GET    /users/{id}`
- `PUT    /users/{id}`
- `POST   /users`
- `DELETE /users/{id}`
- `GET    /users/doctors/details`
- `GET    /users/doctors/{id}/details`
- `PUT    /users/doctors/{id}/details`
- `POST   /users/doctors/{docId}/details`

### 📅 Appointments
- `GET    /appointments`
- `GET    /appointments/{id}`
- `PUT    /appointments/{id}`
- `POST   /appointments`
- `DELETE /appointments/{id}`

### 🩺 Medical Reports
- `GET    /medical-reports`
- `GET    /medical-reports/{id}`
- `PUT    /medical-reports/{id}`
- `POST   /medical-reports`
- `DELETE /medical-reports/{id}`

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Maven
- Lombok
- MySQL

---

## ⚙️ Setup & Run

```bash
git clone https://github.com/your-username/hospital-api.git
cd hospital-api/hospital-coursework
./mvnw clean install
./mvnw spring-boot:run
```

Access the API at: http://localhost:8080/

🔧 Configuration

Edit:

`src/main/resources/application.properties`

To configure:
- DB (MySQL)
- Server port
- Hibernate, logging, etc.
