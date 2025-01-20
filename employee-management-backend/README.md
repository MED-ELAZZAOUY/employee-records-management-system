# Employee Management Backend

This project is a backend application for managing employees, departments, and users within a company. It includes role-based security implemented using Spring Security and JWT authentication.

## Features

- **User Management**: Create, update, delete, and fetch user information with role-based access control.
- **Employee Management**: Manage employees' data with access control for HR, Admin, and Manager roles.
- **Department Management**: Manage department data with role-based access.
- **Security**:
    - JWT-based authentication and authorization.
    - Role-based access control with custom permissions.
    - User and Role entities for managing security.

## Technologies Used

- **Java**: Version 17
- **Spring Boot**: Version 3.4.1
    - Spring Security 6
    - Spring Data JPA
    - Spring Validation
- **Database**: Oracle SQL
- **JWT**: For authentication and authorization
- **Tools**: IntelliJ IDEA, Maven, Git

## Setup and Installation

### Prerequisites
- Java 17
- Maven
- Oracle SQL
- Git

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/MED-ELAZZAOUY/employee-records-management-system.git
   ```
2. Navigate to the project directory:
   ```bash
   cd employee-management-backend
   ```
3. Pull the Oracle database Docker image:
    ```bash
   docker pull fluigfws/oracle12c
   ```
4. Run the Oracle database container:
     ```bash
   docker run -d -p 1521:1521 --name oracle-db fluigfws/oracle12c
   ```
5.  Configure the database in `application.yml` or `application.properties`:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
   spring.datasource.username=system
   spring.datasource.password=oracle
   spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
   ```
6. Run the application
    ```bash
   mvn spring-boot:run
   ```
7. Access the application at `http://localhost:8081/api/`.

## API Endpoints

### Authentication
- **POST** `/auth/login`: Authenticate a user and retrieve a JWT token.

### Users
- **GET** `/users`: Fetch all users (Admin only).
- **POST** `/users`: Create a new user (Admin only).
- **PUT** `/users/{id}`: Update user details (Admin only).
- **DELETE** `/users/{id}`: Delete a user (Admin only).

### Employees
- **GET** `/employees`: Fetch all employees (Roles: Admin, HR, Manager).
- **POST** `/employees`: Create a new employee (Roles: Admin, HR).
- **PUT** `/employees/{id}`: Update employee details (Roles: Admin, HR).
- **DELETE** `/employees/{id}`: Delete an employee (Roles: Admin, HR).

### Departments
- **GET** `/departments`: Fetch all departments (Roles: Admin, HR, Manager).
- **POST** `/departments`: Create a new department (Admin only).
- **PUT** `/departments/{id}`: Update department details (Roles: Admin, HR).
- **DELETE** `/departments/{id}`: Delete a department (Admin only).

