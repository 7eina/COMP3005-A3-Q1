
-- CREATE DATABASE studentsdb;
-- User: postgres
-- Password: 1234

--  students table
CREATE TABLE IF NOT EXISTS students
(
	student_id Serial Primary Key,
	first_name character varying(50) Not Null,
	last_name character varying(50) Not Null,
	email character varying(255) Not Null Unique,
	enrollment_date Date
);

INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');