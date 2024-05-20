package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        boolean isChange = UserService.changePassword(changePasswordDto);
        //boolean isLogin =
        System.out.println(isChange);
    }

}
