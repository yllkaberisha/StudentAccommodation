package controllers;

import app.Navigator;
import app.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import models.dto.LoginUserDto;
import models.User;
import services.UserService;
import utils.AlertUtil;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private void handleLogin(ActionEvent ae){
        LoginUserDto loginUserData = new LoginUserDto(
                this.txtEmail.getText(),
                this.pwdPassword.getText()
        );


        User user = UserService.login(loginUserData);

        if (user != null) {
            SessionManager.setUser(user);
            if (user.getRole().equals("admin")) {
                System.out.println("admin");
                Navigator.navigate(ae, Navigator.ADMIN_MAIN);
            } else if(user.getRole().equals("user")) {
                System.out.println("user");

                Navigator.navigate(ae, Navigator.STUDENT_APPLICATION_PAGE);
            }
        } else {
            // Show login failed message
            System.out.println("Login failed. Invalid username or password.");

            AlertUtil.showErrorAlert(
                    "Login Failed",
                    "Authentication Error",
                    "Your username or password is incorrect. Please try again."
            );

        }

    }

    @FXML
    private void handleForgotPasswordClick(MouseEvent me){
        Navigator.navigate(me,Navigator.CHANGE_PASSWORD_ACCOUNT_PAGE);
    }
    @FXML
    public void handleCreateAccountClick(MouseEvent me)  {
        Navigator.navigate(me,Navigator.CREATE_ACCOUNT_PAGE);
    }

    public void handelChangeLanguage(ActionEvent ae) {
        Navigator.changeLanguage();
        Navigator.navigate(ae,Navigator.LOGIN_PAGE);
    }
}
