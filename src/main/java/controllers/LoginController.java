package controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import models.dto.LoginUserDto;
import services.UserService;

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

        boolean isLogin = UserService.login(loginUserData);
        //boolean isLogin =
        System.out.println(isLogin);
    }

    @FXML
    private void handleForgotPasswordClick(MouseEvent me){
        Navigator.navigate(me,Navigator.CHANGE_PASSWORD_ACCOUNT_PAGE);
    }
    @FXML
    public void handleCreateAccountClick(MouseEvent me)  {
        Navigator.navigate(me,Navigator.CREATE_ACCOUNT_PAGE);
    }
}
