package repositories;

import models.AdminUser;
import models.User;
import models.filter.AdminUserFilter;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
    public List<AdminUser> getAllUsers() {
        List<AdminUser> users = new ArrayList<>();
        String query = "SELECT u.id, u.firstname, u.lastName, u.gender, u.role, u.email, r.roomID AS room " +
                "FROM USERS u " +
                "LEFT JOIN APPLICATION a ON u.id = a.userID " +
                "LEFT JOIN ALLOCATION al ON a.applicationID = al.applicationID " +
                "LEFT JOIN ROOM r ON al.roomID = r.roomID";

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
                "WHERE 1=1 " + filter.getBuildQuery();

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

}
