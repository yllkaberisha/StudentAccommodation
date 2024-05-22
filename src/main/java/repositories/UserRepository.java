package repositories;

import models.User;
import models.dto.ApplicationDto;
import models.dto.ChangeUserPasswordDto;
import models.dto.CreateUserDto;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            int id = result.getInt("id");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String gender = result.getString("gender");
            String role = result.getString("role");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            return new User(id, firstName, lastName, gender, role, email, salt, passwordHash);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static boolean saveInformation(ApplicationDto applicationData) {
        int id = applicationData.getID();
        String faculty = applicationData.getFaculty();
        Integer yearsOfStudies = applicationData.getYearsOfStudies();
        String major = applicationData.getMajor();
        double averageGrade = applicationData.getAverageGrade();

        try (Connection connection = DBConnector.getConnection()) {
            String query = "INSERT INTO application (userID, applicationDate, faculty, yearOfStudies, major, averageGrade, status ) VALUES (?, NOW(), ?, ?, ?, ?, 'pending')";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, faculty);
            preparedStatement.setInt(3, yearsOfStudies);
            preparedStatement.setString(4, major);
            preparedStatement.setDouble(5, averageGrade);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateInformation(ApplicationDto applicationData) {
        String faculty = applicationData.getFaculty();
        Integer yearsOfStudies = applicationData.getYearsOfStudies();
        String major = applicationData.getMajor();
        double averageGrade = applicationData.getAverageGrade();

        try (Connection connection = DBConnector.getConnection()) {
            String query = "UPDATE application SET faculty=?, yearOfStudies=?, major=?, averageGrade=?, status='pending' WHERE applicationid=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, faculty);
            preparedStatement.setInt(2, yearsOfStudies);
            preparedStatement.setString(3, major);
            preparedStatement.setDouble(4, averageGrade);
            preparedStatement.setString(5, "6");
            //  preparedStatement.setInt(5, applicationId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
