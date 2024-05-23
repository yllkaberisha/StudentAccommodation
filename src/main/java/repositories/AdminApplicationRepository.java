package repositories;

import app.SessionManager;
import models.Student;
import models.dto.AdminApplicationDto;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminApplicationRepository {

    private static Connection connection = DBConnector.getConnection();

    public boolean allocationExists(int applicationID) {
        String query = "SELECT COUNT(*) FROM ALLOCATION WHERE applicationID = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, applicationID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly in your application
        }
        return false;
    }


    public void addAllocation(AdminApplicationDto newAdminAppDto) {
        String insertQuery = "INSERT INTO ALLOCATION (applicationID, roomID, allocationDate, userID) VALUES (?, ?, now(), ?)";
        String updateQuery = "UPDATE APPLICATION SET status = ? WHERE applicationID = ?";

        try {
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement insertPst = connection.prepareStatement(insertQuery);
                 PreparedStatement updatePst = connection.prepareStatement(updateQuery)) {

                // Execute insert query
                insertPst.setInt(1, newAdminAppDto.getApplicationID());
                insertPst.setInt(2, newAdminAppDto.getRoomID());
                insertPst.setInt(3, SessionManager.getUser().getID());
                insertPst.executeUpdate();

                // Execute update query
                updatePst.setString(1, newAdminAppDto.getStatus());
                updatePst.setInt(2, newAdminAppDto.getApplicationID());
                updatePst.executeUpdate();

                connection.commit(); // Commit transaction

            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction in case of error
                e.printStackTrace(); // Handle exception properly in your application
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly in your application
        } finally {
            try {
                connection.setAutoCommit(true); // Restore default auto-commit mode
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exception properly in your application
            }
        }
    }

    public List<Student> getAllApplications() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT u.id, u.firstname, u.lastName, u.gender, a.applicationID, a.faculty, a.major, a.averageGrade, a.status, a.yearOfStudies, al.roomID " +
                "FROM USERS u " +
                "JOIN APPLICATION a ON u.id = a.userID " +
                "left join allocation al ON al.applicationID = a.applicationID";

        System.out.println(query);
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

    private Student getFromResultSet(ResultSet result) throws SQLException {
        System.out.println("result");
        int applicationID = result.getInt("applicationID");
        int userId = result.getInt("id");
        String firstName = result.getString("firstname");
        String lastName = result.getString("lastName");
        String gender = result.getString("gender");
        String status = result.getString("status");
        Integer room = result.getInt("roomID");
        if (result.wasNull()) {
            room = null;
        }
        String faculty = result.getString("faculty");
        String major = result.getString("major");
        double averageGrade = result.getDouble("averageGrade");
        int year = result.getInt("yearOfStudies");

        return new Student(applicationID, userId, firstName, lastName, gender, status, room, faculty, major, averageGrade, year);
    }

    public void updateAllocation(AdminApplicationDto newAdminAppDto) {
        String updateAllocationQuery = "UPDATE ALLOCATION SET roomID = ?, allocationDate = now(), userID = ? WHERE applicationID = ?";
        String updateStatusQuery = "UPDATE APPLICATION SET status = ? WHERE applicationID = ?";

        try {
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement updateAllocationPst = connection.prepareStatement(updateAllocationQuery);
                 PreparedStatement updateStatusPst = connection.prepareStatement(updateStatusQuery)) {

                // Execute update allocation query
                updateAllocationPst.setInt(1, newAdminAppDto.getRoomID());
                updateAllocationPst.setInt(2, SessionManager.getUser().getID());
                updateAllocationPst.setInt(3, newAdminAppDto.getApplicationID());
                updateAllocationPst.executeUpdate();

                // Execute update status query
                updateStatusPst.setString(1, newAdminAppDto.getStatus());
                updateStatusPst.setInt(2, newAdminAppDto.getApplicationID());
                updateStatusPst.executeUpdate();

                connection.commit(); // Commit transaction

            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction in case of error
                e.printStackTrace(); // Handle exception properly in your application
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly in your application
        } finally {
            try {
                connection.setAutoCommit(true); // Restore default auto-commit mode
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exception properly in your application
            }
        }
    }

}
