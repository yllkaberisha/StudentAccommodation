package controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.User;
import models.dto.ChangePasswordDto;
import services.UserService;

public class ChangePasswordController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdNewPassword;
    @FXML
    private PasswordField pwdConfirmPassword;
    @FXML
    public void handleChangePassword(ActionEvent ae){
        ChangePasswordDto changePasswordDto = new ChangePasswordDto(
                this.txtEmail.getText(),
                this.pwdNewPassword.getText(),
                this.pwdConfirmPassword.getText()
        );
        User user = UserService.changePassword(changePasswordDto);

    }
    @FXML
    public void handleBack(MouseEvent me){
        Navigator.navigate(me,Navigator.LOGIN_PAGE);
    }

}
