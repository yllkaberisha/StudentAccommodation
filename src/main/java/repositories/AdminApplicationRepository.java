package repositories;

import models.Student;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminApplicationRepository {

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT u.id, u.firstname, u.lastName, u.gender, a.faculty, a.major, a.averageGrade, a.status, a.yearOfStudies, u.role " +
                "FROM USERS u " +
                "JOIN APPLICATION a ON u.id = a.userID " +
                "WHERE u.isActive = TRUE";

        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                students.add(getFromResultSet(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    private static Student getFromResultSet(ResultSet result){
        try {
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String gender = result.getString("gender");
            String faculty = result.getString("faculty");
            String major = result.getString("major");
            String averageGrade = result.getString("averageGrade");
            String status = result.getString("status");
            String yearOfStudies = result.getString("yearOfStudies");
            String userId = result.getString("userId");
            return new Student(firstName, lastName, gender, faculty, major, averageGrade, status, yearOfStudies, userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(Student student) {
        String query = "INSERT INTO APPLICATION (userID, applicationDate, status, faculty, yearOfStudies, major, averageGrade) VALUES (?, ?, ?, ?, ?, ?, ?);";

        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, student.getFirstName());
            pst.setString(2, student.getLastName());
            pst.setString(3, student.getGender());
            pst.setString(4, student.getUserId()); // Assuming role is stored in userId
            pst.setString(5, ""); // Assuming email is empty for now
            pst.setString(6, ""); // Assuming passwordHash is empty for now
            pst.setString(7, ""); // Assuming salt is empty for now
            pst.setInt(8, Integer.parseInt(student.getUserId())); // Assuming userID is parsed from student ID
            pst.setDate(9, new java.sql.Date(System.currentTimeMillis())); // Assuming applicationDate is current date
            pst.setString(10, student.getStatus());
            pst.setString(11, student.getFaculty());
//            pst.setInt(12, Integer.parseInt(student.getYearOfStudies()));
            pst.setString(13, student.getMajor());
            pst.setBigDecimal(14, new java.math.BigDecimal(student.getAverageGrade()));
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Student student) {
        Connection connection = DBConnector.getConnection();
        try {
            connection.setAutoCommit(false); // Start transaction

            // Update USERS table
            String updateUsersQuery = "UPDATE USERS SET firstName = ?, lastName = ?, gender = ? WHERE id = ?";
            PreparedStatement pstUsers = connection.prepareStatement(updateUsersQuery);
            pstUsers.setString(1, student.getFirstName());
            pstUsers.setString(2, student.getLastName());
            pstUsers.setString(3, student.getGender());
//            pstUsers.setInt(4, Integer.parseInt(student.getId()));
            pstUsers.executeUpdate();

            // Update APPLICATION table
            String updateApplicationQuery = "UPDATE APPLICATION SET faculty = ?, major = ?, averageGrade = ?, status = ?, yearOfStudies = ? WHERE userID = ?";
            PreparedStatement pstApplication = connection.prepareStatement(updateApplicationQuery);
            pstApplication.setString(1, student.getFaculty());
            pstApplication.setString(2, student.getMajor());
            pstApplication.setBigDecimal(3, new java.math.BigDecimal(student.getAverageGrade()));
            pstApplication.setString(4, student.getStatus());
//            pstApplication.setInt(5, Integer.parseInt(student.getYearOfStudies()));
//            pstApplication.setInt(6, Integer.parseInt(student.getId()));
            pstApplication.executeUpdate();

            connection.commit(); // Commit transaction
        } catch (Exception e) {
            try {
                connection.rollback(); // Rollback transaction on error
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true); // Restore auto-commit mode
            } catch (Exception autoCommitException) {
                autoCommitException.printStackTrace();
            }
        }
    }

    public void delete(Student student) {
        String query = "UPDATE USERS SET isActive = FALSE WHERE id = ?";
        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
//            pst.setInt(1, Integer.parseInt(student.getId()));
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
