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
import utils.AlertUtil;

public class ChangePasswordController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdNewPassword;
    @FXML
    private PasswordField pwdConfirmPassword;
    @FXML
    public void handleChangePassword(ActionEvent ae) {
        ChangePasswordDto changePasswordDto = new ChangePasswordDto(
                this.txtEmail.getText(),
                this.pwdNewPassword.getText(),
                this.pwdConfirmPassword.getText()
        );
        boolean changed = UserService.changePassword(changePasswordDto);

        if (changed) {
            AlertUtil.showSuccessAlert(
                    "Password Change Success",
                    "Password Updated",
                    "Your password has been successfully updated."
            );
//            System.out.println("Navigating to login page...");
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
        } else {
            AlertUtil.showErrorAlert(
                    "Password Change Failed",
                    "Update Error",
                    "There was an error updating your password. Please try again."
            );
        }
    }
    @FXML
    public void handleBack(MouseEvent me){
        Navigator.navigate(me,Navigator.LOGIN_PAGE);
    }

    @FXML
    public void handelChangeLanguage(ActionEvent ae) {
        Navigator.changeLanguage();
        Navigator.navigate(ae,Navigator.CHANGE_PASSWORD_ACCOUNT_PAGE);
    }
}
