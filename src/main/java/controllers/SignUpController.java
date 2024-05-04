package controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.dto.UserDto;
import services.UserService;

import java.io.IOException;

public class SignUpController {
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private PasswordField pwdConfirmPassword;

    @FXML
    private void handleSignUp(ActionEvent ae) throws IOException {
        UserDto userSignUpData = new UserDto(
                this.txtFirstName.getText(),
                this.txtLastName.getText(),
                this.txtGender.getText(),
                this.txtRole.getText(),
                this.txtEmail.getText(),
                this.pwdPassword.getText(),
                this.pwdConfirmPassword.getText()
        );

        boolean response = UserService.signUp(userSignUpData);

        if(response){
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
        }

    }

    @FXML
    private void handleCancel(ActionEvent ae){

    }
}