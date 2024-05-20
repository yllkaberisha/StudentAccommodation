package repositories;

import models.dto.ChangePasswordDto;
import models.dto.CreateUserDto;
import models.User;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

    public static boolean create(CreateUserDto userData){
        Connection conn = DBConnector.getConnection();
        String query = """
                INSERT INTO USERS (firstname, lastName, gender, role, email, passwordHash, salt)
                VALUE (?, ?, ?, ?, ?, ?, ?)
                """;
        //String query = "INSERT INTO USER VALUE (?, ?, ?, ?, ?)";
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
            return false;
        }

    }
    public static boolean change(ChangePasswordDto changePasswordData){
        Connection conn = DBConnector.getConnection();
        String query1 = """
                UPDATE USERS
                SET newPassword = ?, confirmPassword = ?
                WHERE email = ?
                """;
        //String query = "INSERT INTO USER VALUE (?, ?, ?, ?, ?)";
        try{
            PreparedStatement pst = conn.prepareStatement(query1);
            pst.setString(1, changePasswordData.getEmail());
            pst.setString(2, changePasswordData.getNewPassword());
            pst.setString(3, changePasswordData.getConfirmPassword());

            pst.execute();
            pst.close();
            conn.close();
            return true;
        }catch (Exception e){
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
            return null;
        }
    }

    private static User getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("id");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String gender = result.getString("gender");
            String role = result.getString("role");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            return new User(
                     firstName, lastName, gender, role, email, salt, passwordHash
            );
        }catch (Exception e){
            return null;
        }
    }







}