package org.zeina.comp3005;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Date;


public class Main {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";
    private static final String URL = "jdbc:postgresql://localhost:5432/studentsdb";
    private static Scanner scanner; // to get user inputs
    private static Statement statement; // this is to run sql query string
    private static Connection connection; // manages connection



    public static void main(String[] args) throws ParseException {

        try {
            // establish connection to database
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // create Statement for CRUD operations on database
            statement = connection.createStatement();
        }
        catch (SQLException sqlex) {
            // exit and print error
            System.err.println("Unable to connect");
            sqlex.printStackTrace();
            System.exit(1);
        }

        // create Scanner for user input
        scanner = new Scanner(System.in);

        // var for menu options
        int option = getOption();

        // process user request - 5  - to exit
        while (option != 5) {
            switch(option) {
                case 1: // select all students
                    getAllStudents();
                    break;
                case 2: // add student
                    System.out.println("Please enter student's first name: ");
                    String firstName = scanner.next();
                    System.out.println("Please enter student's last name: ");
                    String lastName = scanner.next();
                    System.out.println("Please enter student's email: ");
                    String email = scanner.next();
                    System.out.println("Please enter enrollment date (YYYY-MM-DD): ");
                    String enrolDateString = scanner.next();
                    Date enrolDate = java.sql.Date.valueOf(enrolDateString);
                    addStudent(firstName, lastName, email, enrolDate);
                    break;
                case 3: // update student email
                    System.out.println("Please enter student's Id: ");
                    int studentId = scanner.nextInt();
                    System.out.println("Please enter new email: ");
                    String newEmail = scanner.next();
                    updateStudentEmail(studentId, newEmail);
                    break;
                case 4: // delete  student
                    System.out.println("Please enter student's Id to delete: ");
                    int studentIdDel = scanner.nextInt();
                    deleteStudent(studentIdDel);
                    break;
                default:
                    System.out.println("Enter a number from 1-5");
            }

            option = getOption();
        }
    }
    // get user choice
    private static int getOption() {
        System.out.println("Please choose one action:");
        System.out.println("1 -- select all students");
        System.out.println("2 -- add a student");
        System.out.println("3 -- update student email");
        System.out.println("4-- delete  student");
        System.out.println("5 -- exit");
        return scanner.nextInt(); // get user choice
    }


    /*
     * Retrieves and displays all records from the students table.
     * in: N/A
     * out: returns table of ALL students in DB
     */
    private static void getAllStudents() {

        String query = "SELECT * FROM students";
        try {
                // execute query
                ResultSet rs = statement.executeQuery(query);
                // process query results
                ResultSetMetaData md = rs.getMetaData();
                int numFields = md.getColumnCount();

                for (int i = 1; i <= numFields; i++) {
                    System.out.printf("%-12s\t", md.getColumnName(i));
                }
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= numFields; i++) {
                        System.out.printf("%-12s\t", rs.getObject(i));
                    }
                    System.out.println();
                }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /*
    * Inserts a new student record into the students table.
    * in: first name, last name, email, enrollment date
    * out: adds new student with specified details to DB
    */
    private static void addStudent(String firstName, String lastName, String email, Date enrollementDate){
        String insertSQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('" + firstName + "', '" + lastName + "', '" + email + "', '" + enrollementDate + "')";
        try {
            System.out.printf("Executing SQL: %s\n", insertSQL);
            int result = statement.executeUpdate(insertSQL);
            // display result of SQL execution
            if (result == 1) {
                System.out.println("Insert successful\n");
            }
            else {
                System.out.println("Insert failed\n");
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /*
    * Updates the email address for a student with the specified student_id
    * in: student_id
    * out: updated student email
    * */
    private static void updateStudentEmail(int studentId, String email){

        String updateSQL = "UPDATE students SET email = '" + email + "' WHERE student_id = " + studentId;
        try {
            System.out.printf("Executing SQL: %s\n", updateSQL);
            int result = statement.executeUpdate(updateSQL);
            // display result of SQL execution
            if (result == 1) {
                System.out.println("Update successful\n");
            }
            else {
                System.out.println("Update failed\n");
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /*
     * Deletes the record of the student with the specified student_id.
     * in: styudent_id
     * out: student with specified ID is removed from student list
     * */
    private static void deleteStudent(int studentId){
        String deleteSQL = "DELETE FROM students WHERE student_id = " + studentId;
        try {
            System.out.printf("Executing SQL: %s\n", deleteSQL);
            int result = statement.executeUpdate(deleteSQL);
            // display result of SQL execution
            if (result == 1) {
                System.out.println("Delete successful\n");
            }
            else {
                System.out.println("Delete failed\n");
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}