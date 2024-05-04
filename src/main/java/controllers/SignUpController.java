package controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
    @FXML
    public void initialize() {
        // Vendos fokusin në txtFirstName kur skena ngarkohet
        txtFirstName.requestFocus();

        // Vendos një event handler për tastin Enter në txtFirstName për të kaluar në txtLastName
        txtFirstName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtLastName.requestFocus();
            }
        });

        // Mund të shtoni event handler të ngjashëm për të kaluar në fushat e tjera të tekstit
        txtLastName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtGender.requestFocus();
            }
        });
        txtGender.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtRole.requestFocus();
            }
        });
        txtRole.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmail.requestFocus();
            }
        });
        txtEmail.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                pwdPassword.requestFocus();
            }
        });
        pwdPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                pwdConfirmPassword.requestFocus();
            }
        });


        // Vazhdoni për fushat e tjera të tekstit nëse është e nevojshme
    }
}

