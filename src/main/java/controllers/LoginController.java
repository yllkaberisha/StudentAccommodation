package controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.dto.LoginUserDto;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private void handleLogin(ActionEvent ae){
        LoginUserDto loginUserDto = new LoginUserDto(
                this.txtEmail.getText(),
                this.pwdPassword.getText()
        );
        //boolean isLogin =
    }

    @FXML
    private void handleCancelClick(ActionEvent ae){

    }
    @FXML
    public void handleCreateAccountClick(MouseEvent me) throws IOException {
        Navigator.navigate(me,Navigator.CREATE_ACCOUNT_PAGE);
    }
}
