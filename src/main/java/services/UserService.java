package services;

import models.User;
import models.dto.ChangePasswordDto;
import models.dto.CreateUserDto;
import models.dto.LoginUserDto;
import models.dto.UserDto;
import repositories.UserRepository;

public class UserService {
    public static boolean signUp(UserDto userData) {
        String password = userData.getPassword();
        String confirmPassword = userData.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            return false;
        }
        String salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(
                password, salt
        );
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

    public static boolean login(LoginUserDto loginData){
        User user = UserRepository.getByEmail(loginData.getEmail());
        if(user == null){
            return false;
        }

        String password = loginData.getPassword();
        String salt = user.getSalt();
        String passwordHash = user.getPasswordHash();

        return PasswordHasher.compareSaltedHash(
                password, salt, passwordHash
        );
    }
    public static boolean changePassword(ChangePasswordDto changePasswordData){
        User user = UserRepository.getByEmail(changePasswordData.getEmail());
        if (user==null){
            return false;
        }
        String newPassword = changePasswordData.getNewPassword();
        String confirmPassword = changePasswordData.getConfirmPassword();
        ChangePasswordDto changePasswordDto = new ChangePasswordDto(
                changePasswordData.getEmail(),
                changePasswordData.getNewPassword(),
                changePasswordData.getConfirmPassword()

        );

        return UserRepository.change(changePasswordDto);
    }
    }




