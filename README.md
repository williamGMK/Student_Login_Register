# PRG-User-Login Web App

A Java Servlet + JSP-based user authentication system with PostgreSQL integration. This application allows users to register and log in securely with hashed passwords, while storing user data in a PostgreSQL database.

---

## 📦 Features

- ✅ User Registration with validation (email, password strength)
- ✅ Password hashing using Java security libraries
- ✅ Login with session management
- ✅ PostgreSQL database integration
- ✅ Error and success feedback on UI

---

## 🚀 Technologies Used

- Java Servlet & JSP (Jakarta EE 10)
- PostgreSQL 17+
- Maven (for dependency management)
- NetBeans (recommended IDE)
- HTML & Bootstrap (for UI)
- JDBC (Java Database Connectivity)

---

## 🧰 Prerequisites

- [Java JDK 17+](https://adoptopenjdk.net/)
- [Apache Tomcat 9.1.x](https://tomcat.apache.org/download-10.cgi)
- [NetBeans (Jakarta EE support)](https://netbeans.apache.org/download/index.html)
- [PostgreSQL 17+](https://www.postgresql.org/download/)

---

## 🐘 PostgreSQL Installation

### 🔽 Step 1: Install PostgreSQL

1. Download installer: [https://www.postgresql.org/download/windows/](https://www.postgresql.org/download/windows/)
2. Run the installer and:
   - Choose your preferred password for the `postgres` user
   - Enable pgAdmin if prompted
3. After installation, open **pgAdmin** to manage the database

---

## 🗃️ Database Setup

### Step 2: Create the database 

You can use `psql` or pgAdmin:

```sql
CREATE DATABASE User-login;
Step 3: Create users_table;
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
File Structure:

PRG-User-Login/
│
├── src/
│   └── java/
│       └── com.javam/
│           ├── servlet/
│           │   ├── RegisterServlet.java
│           │   ├── LoginServlet.java
│           │   └── LogoutServlet.java
│           └── model/
│               ├── User.java
│               ├── UserDAO.java
│               └── DBConnection.java
│
├── web/
│   ├── index.jsp
│   ├── register.jsp
│   ├── login.jsp
│   └── home.jsp
│
└── pom.xml
