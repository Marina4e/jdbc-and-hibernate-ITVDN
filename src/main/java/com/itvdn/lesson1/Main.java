package com.itvdn.lesson1;

import com.itvdn.lesson1.util.ConnectionManager;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String createStudentTableSql = """
                    CREATE TABLE IF NOT EXISTS students (
                        id INT PRIMARY KEY,
                        first_name VARCHAR(50),
                        last_name VARCHAR(50),
                        email VARCHAR(100)
                    );
                """;

        String createCoursesTableSql = """
                    CREATE TABLE IF NOT EXISTS courses (
                        id INT PRIMARY KEY,
                        course_name VARCHAR(100),
                        description VARCHAR(1000),
                        duration INT
                    );
                """;

        String createEnrollmentTableSql = """
                    CREATE TABLE IF NOT EXISTS enrollment (
                        id INT PRIMARY KEY,
                        student_id INT,
                        course_id INT,
                        FOREIGN KEY (student_id) REFERENCES students(id),
                        FOREIGN KEY (course_id) REFERENCES courses(id)
                    );
                """;

        String insertStudentsSql = """
                    INSERT INTO students (id, first_name, last_name, email) VALUES
                    (1, 'Іван', 'Коваленко', 'ivan.kovalenko@example.com'),
                    (2, 'Олена', 'Петренко', 'olena.petrenko@example.com'),
                    (3, 'Максим', 'Шевченко', 'maxim.shevchenko@example.com');
                """;


        String insertCoursesSql = """
                    INSERT INTO courses (id, course_name, description, duration) VALUES
                    (1, 'Introduction to Java', 'Basic Java programming', 30),
                    (2, 'JDBC Basics', 'Intoduction to JDBC', 25);
                """;


        String insertEnrollmentSql = """
                    INSERT INTO enrollment (id, course_id, student_id) VALUES
                    (1, 1, 1),
                    (2, 1, 2),
                    (3, 2, 2),
                    (4, 2, 3);
                """;

        String dropStudents = """
                    DROP TABLE students;
                """;
        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement()) {
            statement.execute(createStudentTableSql);
//            statement.execute(createCoursesTableSql);
//            statement.execute(createEnrollmentTableSql);
//            statement.execute(insertCoursesSql);
//            statement.execute(insertEnrollmentSql);
//            statement.execute(insertStudentsSql);
//            statement.execute(dropStudents);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
