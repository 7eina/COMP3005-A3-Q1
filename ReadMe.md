# Student Database Management System

[Demo Link](https://youtu.be/0XlsFlrvkoo) 

***Note to Viewer***
- At the time this demo was recorded, I was highly congested, so apologies for the audio :/
- I am a light mode user... you have been warned...

## Project Description

This project involves the implementation of a PostgreSQL database according to a specified 
schema and the development of a Java application that connects to this database. The application 
supports various CRUD (Create, Read, Update, Delete) operations, allowing manipulation of student 
records within the database.

## Application Functions

- The Java application provides a simple text-based user interface to interact with the student database, supporting operations to view, add, update, and delete student records.

## System Requirements

- IntelliJ IDEA
- JDK 21
- pgAdmin 4

## Project Structure

### `/src`
Contains the source code for the Java application. Notable files include:

- `Main.java`: The primary Java application file responsible for connecting to the PostgreSQL database, providing a command-line interface for interacting with the database, and performing CRUD operations. It includes methods for adding, updating, deleting, and retrieving student records.

### `/target`
This directory is typically generated when the project is built and may contain compiled code and other build artifacts, including:

- `Main.class`: The compiled version of `Main.java`, ready for execution.

### `/pom.xml`
Includes project configuration details, dependencies, and build settings for Maven. This file is for managing the project's dependencies and ensuring that the build process is consistent across different environments.

### `/sql`
Holds SQL scripts used to set up and interact with the database:

- `students_db.sql`: Contains SQL commands for creating the `students` table and inserting initial records.

## Installation and Usage Instructions

1. **Database Setup**:
    - Launch pgAdmin 4 and create a new database named `studentsdb`.
    - Execute the SQL script from the `/sql` directory to create your database schema and populate it with initial data.
    - OR use the following commands to create and populate the `students` table:

      ```sql
      CREATE TABLE students (
        student_id SERIAL PRIMARY KEY,
        first_name TEXT NOT NULL,
        last_name TEXT NOT NULL,
        email TEXT NOT NULL UNIQUE,
        enrollment_date DATE
      );
 
      INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
      ('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
      ('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
      ('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');
      ```

2. **Application Execution**:
    - Download and open the project in IntelliJ IDEA.
    - Verify that JDK 21 is configured correctly in IntelliJ IDEA for the project.
    - Run the `Main` class to start the application.
    - Use the console interface to interact with the database by selecting the appropriate options to perform CRUD operations:

      ```
      1 - Select all students
      2 - Add a student
      3 - Update student email
      4 - Delete a student
      5 - Exit
      ```

## Note on Error Handling and Data Validation

- Error handling is implemented to manage any issues that arise during database connection and interaction, as outlined in the evaluation rubric.
- Data validation, specifically for verifying the format and type of user inputs (e.g., ensuring dates are numeric), is not included as it was beyond the scope of the assignment requirements.
