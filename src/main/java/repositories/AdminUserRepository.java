package repositories;

import models.AdminUser;
import models.filter.AdminUserFilter;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminUserRepository {
    public List<AdminUser> getAllUsers() {
        List<AdminUser> users = new ArrayList<>();
        String query = "SELECT u.id, u.firstname, u.lastName, u.gender, u.role, u.email, r.roomID AS room " +
                "FROM USERS u " +
                "LEFT JOIN APPLICATION a ON u.id = a.userID " +
                "LEFT JOIN ALLOCATION al ON a.applicationID = al.applicationID " +
                "LEFT JOIN ROOM r ON al.roomID = r.roomID " +
                "WHERE u.isActive = TRUE";


        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                users.add(getFromResultSet(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    private static AdminUser getFromResultSet(ResultSet result){
        try{
            String id = result.getString("ID");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String gender = result.getString("gender");
            String role = result.getString("role");
            String email = result.getString("email");
            String room = result.getString("room");
            return new AdminUser(id,firstName, lastName,email, room, gender, role );
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<AdminUser> getByFilter(AdminUserFilter filter){
        List<AdminUser> users = new ArrayList<>();
        String query = "SELECT u.id, u.firstname, u.lastName, u.gender, u.role, u.email, r.roomID AS room FROM USERS u " +
                "LEFT JOIN APPLICATION a ON u.id = a.userID " +
                "LEFT JOIN ALLOCATION al ON a.applicationID = al.applicationID " +
                "LEFT JOIN ROOM r ON al.roomID = r.roomID " +
                "WHERE u.isActive = TRUE " + filter.getBuildQuery();

        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                users.add(getFromResultSet(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void updateUser(AdminUser selectedUser) {
        Connection connection = DBConnector.getConnection();
        try {
            connection.setAutoCommit(false); // Start transaction

            // Update USERS table
            String updateUsersQuery = "UPDATE USERS SET firstName = ?, lastName = ?, gender = ?, role = ?, email = ? WHERE id = ?";
            PreparedStatement pstUsers = connection.prepareStatement(updateUsersQuery);
            pstUsers.setString(1, selectedUser.getFirstName());
            pstUsers.setString(2, selectedUser.getLastName());
            pstUsers.setString(3, selectedUser.getGender());
            pstUsers.setString(4, selectedUser.getRole());
            pstUsers.setString(5, selectedUser.getEmail());
            pstUsers.setString(6, selectedUser.getId());
            pstUsers.executeUpdate();

            // Update ALLOCATION table
                String updateAllocationQuery = "UPDATE ALLOCATION SET roomID = ? WHERE userID = ?";
                PreparedStatement pstAllocation = connection.prepareStatement(updateAllocationQuery);
                pstAllocation.setString(1, selectedUser.getRoom());
                pstAllocation.setString(2, selectedUser.getId());
                pstAllocation.executeUpdate();
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

    public void deleteUser(AdminUser selectedUser) {
        String query = "UPDATE USERS SET isActive = FALSE WHERE id = ?";
        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, selectedUser.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
