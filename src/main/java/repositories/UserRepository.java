package repositories;

import models.User;
import models.dto.ChangeUserPasswordDto;
import models.dto.CreateUserDto;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static boolean create(CreateUserDto userData){
        Connection conn = DBConnector.getConnection();
        String query = """
                INSERT INTO USERS (firstname, lastName, gender, role, email, passwordHash, salt)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userData.getFirstName());
            pst.setString(2, userData.getLastName());
            pst.setString(3, userData.getGender());
            pst.setString(4, userData.getRole());
            pst.setString(5, userData.getEmail());
            pst.setString(6, userData.getPasswordHash());
            pst.setString(7, userData.getSalt());
            pst.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean change(ChangeUserPasswordDto changeUserPasswordData){
        Connection conn = DBConnector.getConnection();
        String query = """
                UPDATE USERS
                SET salt = ?, passwordHash = ?
                WHERE email = ?
                """;
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, changeUserPasswordData.getSalt());
            pst.setString(2, changeUserPasswordData.getPasswordHash());
            pst.setString(3, changeUserPasswordData.getEmail());
            pst.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static User getByEmail(String email){
        String query = "SELECT * FROM USERS WHERE email = ? LIMIT 1";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM USERS";
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

    private static User getFromResultSet(ResultSet result){
        try{
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String gender = result.getString("gender");
            String role = result.getString("role");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            return new User(firstName, lastName, gender, role, email, salt, passwordHash);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
