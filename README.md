<div align="center">

# 🎓 Online Class & Enrollment Management System

**A Java Swing & MySQL Desktop Application for Streamlining Student Registration, Class Enrollment, and Payment Approvals**

[![Java](https://img.shields.io/badge/Java-11-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/11/)
[![Swing](https://img.shields.io/badge/UI-Java%20Swing-007396?style=for-the-badge&logo=java&logoColor=white)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![JDBC](https://img.shields.io/badge/Connectivity-JDBC-007396?style=for-the-badge)](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
[![Architecture](https://img.shields.io/badge/Architecture-MVC-brightgreen?style=for-the-badge)]()

[Overview](#-overview) • [Key Features](#-key-features) • [Architecture & OOP](#-architecture--oop-principles) • [Tech Stack](#-tech-stack) • [Getting Started](#-getting-started) • [Future Scope](#-future-improvements)

</div>

---

## 📌 Overview

The **Online Class & Enrollment Management System** is a desktop application built with **Java 11** and **MySQL** to modernize and simplify everyday academic and administrative workflows in educational institutions.

By replacing manual paperwork and scattered records with a centralized desktop interface, the system makes student registration, subject/schedule selection, bank slip verification, and class management faster, cleaner, and less error-prone.

---

## ✨ Key Features

<details>
<summary><b>👨‍🎓 Student Portal — Click to expand</b></summary>

* **Account Registration & Authentication:** Create student accounts and log in securely to access personal academic records.
* **Profile Management:** View and update personal details as needed.
* **Class & Schedule Browsing:** Explore available classes, filter or select subjects, and check class schedules.
* **Bank Payment Slip Upload:** Submit payment proof for enrolled classes directly through the portal.
* **Enrollment & Payment History:** Track active enrollments, pending/approved/rejected payments, and past records in one place.

</details>

<details>
<summary><b>🛠️ Admin Dashboard — Click to expand</b></summary>

* **Student Management:** View, manage, and maintain student records across the institution.
* **Class & Schedule Management:** Add new classes, update existing class details, remove outdated classes, and organize schedules.
* **Payment Slip Verification:** Review bank payment slips uploaded by students and **approve** or **reject** them with clear status updates.

</details>

---

## 🏗️ Architecture & OOP Principles

This project follows the **Model-View-Controller (MVC)** pattern to separate data, user interfaces, and application logic cleanly:

* **Model:** Represents entities (Students, Classes, Schedules, Enrollments, Payments) and handles database operations via **JDBC** and **Prepared Statements**.
* **View:** Built with **Java Swing** to deliver dedicated, user-friendly screens for the **Student Portal** and **Admin Dashboard**.
* **Controller:** Coordinates user actions, validates inputs, manages workflows, and connects the Swing views with the backend models.

### Core OOP & Engineering Highlights
* **Encapsulation:** Private entity fields exposed safely through getters, setters, and controlled service/model methods.
* **Inheritance:** Shared attributes and behaviors structured cleanly across specialized classes/components.
* **Polymorphism:** Flexible method overriding and interface/base-class handling across application components.
* **Abstraction:** Hiding complex SQL and database connection details behind clean service/DAO layers.
* **Singleton Design Pattern:** Ensures a single, controlled shared instance where appropriate (such as database connection management).
* **Prepared SQL Statements & Exception Handling:** Protects against SQL injection and gracefully handles runtime or database errors.

---

## 🧰 Tech Stack

| Category | Technology |
| :--- | :--- |
| **Language** | Java 11 |
| **GUI Framework** | Java Swing |
| **Database** | MySQL |
| **Database Driver** | JDBC (MySQL Connector/J) |
| **Design Pattern** | MVC, Singleton |

---

## 🚀 Getting Started

<details>
<summary><b>1. Prerequisites</b></summary>

* **JDK 11** (or compatible Java 11 distribution) installed and configured in `JAVA_HOME`
* **MySQL Server** running locally or on a reachable host
* **MySQL JDBC Driver (`.jar`)** added to your project classpath / IDE libraries
* An IDE such as **IntelliJ IDEA**, **Eclipse**, or **NetBeans** (optional, CLI compilation also works)

</details>

<details>
<summary><b>2. Database Setup</b></summary>

1. Open MySQL and create a database for the system:
   ```sql
   CREATE DATABASE online_class_enrollment_db;
   USE online_class_enrollment_db;
