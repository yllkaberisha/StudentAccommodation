package services;

import models.User;
import models.dto.*;
import repositories.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserRepository userRepository = new UserRepository();

    public static boolean signUp(UserDto userData) {
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            return false;
        }
        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(password, salt);
        CreateUserDto createUserData = new CreateUserDto(
                userData.getFirstName(),
                userData.getLastName(),
                userData.getGender(),
                userData.getRole(),
                userData.getEmail(),
                passwordHash,
                salt
        );

        return UserRepository.create(createUserData);
    }

    public static User login(LoginUserDto loginData){
        User user = UserRepository.getByEmail(loginData.getEmail());
        if(user == null){
            return null;
        }

        String password = loginData.getPassword();
        String salt = user.getSalt();
        String passwordHash = user.getPasswordHash();

        if (PasswordHasher.compareSaltedHash(password, salt, passwordHash)) {
            return user;
        } else {
            return null;
        }
    }

    public static boolean changePassword(ChangePasswordDto changePasswordData){
        User user = UserRepository.getByEmail(changePasswordData.getEmail());
        if (user == null) {
            return false;
        }

        String confirmPassword = changePasswordData.getConfirmPassword();
        String password = changePasswordData.getNewPassword();

        if (!password.equals(confirmPassword)) {
            return false;
        }
        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(password, salt);
        ChangeUserPasswordDto changeUserPasswordDto = new ChangeUserPasswordDto(
                user.getEmail(),
                passwordHash,
                salt
        );

        return UserRepository.change(changeUserPasswordDto);
    }

    public static List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public static long countMaleUsers(List<User> users) {
        return users.stream().filter(user -> "M".equals(user.getGender())).count();
    }

    public static long countFemaleUsers(List<User> users) {
        return users.stream().filter(user -> "F".equals(user.getGender())).count();
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
            preparedStatement.setString(5, "5");
          //  preparedStatement.setInt(5, applicationId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static int getApplicationId(String faculty, int yearsOfStudies, String major, double averageGrade) {
        try (Connection connection = DBConnector.getConnection()) {
            String query = "SELECT id FROM application WHERE faculty=? AND yearOfStudies=? AND major=? AND averageGrade=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, faculty);
            preparedStatement.setInt(2, yearsOfStudies);
            preparedStatement.setString(3, major);
            preparedStatement.setDouble(4, averageGrade);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if application ID is not found
    }
}
