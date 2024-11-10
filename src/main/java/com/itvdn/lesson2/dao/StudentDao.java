package com.itvdn.lesson2.dao;

import com.itvdn.lesson1.util.ConnectionManager;
import com.itvdn.lesson2.entity.Student;

import java.sql.SQLException;
import java.sql.Statement;

public class StudentDao {

    private static final StudentDao INSTANCE = new StudentDao();
    private static final String SAVE_SQL = """
                INSERT INTO students (first_name, last_name, email) 
                VALUES (?, ?, ?);
            """;
    private static final String FIND_BY_ID_SQL = """
                SELECT * FROM students WHERE id = ?;
            """;

    private StudentDao() {
    }

    public static StudentDao getInstance() {
        return INSTANCE;
    }

    public Student save(Student student) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection
                     .prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getEmail());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt("id"));
            }
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student findById(Integer id) {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection
                     .prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var studentById = preparedStatement.executeQuery();
            Student student = new Student();
            if (studentById.next()) {
                student.setId(studentById.getInt("id"));
                student.setFirstName(studentById.getString("first_name"));
                student.setLastName(studentById.getString("last_name"));
                student.setEmail(studentById.getString("email"));
            }
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
