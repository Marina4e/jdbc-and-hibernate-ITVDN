package com.itvdn.lesson2;

import com.itvdn.lesson1.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTransaction {

    public static void main(String[] args) throws SQLException {
        Integer courseId = 2;

        String deleteEnrollment = "DELETE FROM enrollment WHERE course_id = ?";
        String deleteCourses = "DELETE FROM courses WHERE id = ?";
        Connection connection = null;
        PreparedStatement deleteEnrollmentStatement = null;
        PreparedStatement deleteCoursesStatement = null;

        try {
            connection = ConnectionManager.open();
            deleteEnrollmentStatement = connection.prepareStatement(deleteEnrollment);
            deleteCoursesStatement = connection.prepareStatement(deleteCourses);

            connection.setAutoCommit(false);

            deleteEnrollmentStatement.setInt(1, courseId);
            deleteCoursesStatement.setInt(1, courseId);

            deleteEnrollmentStatement.executeUpdate();

            deleteCoursesStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteCoursesStatement != null) {
                deleteCoursesStatement.close();
            }
            if (deleteEnrollmentStatement != null) {
                deleteEnrollmentStatement.close();
            }
        }
    }
}
