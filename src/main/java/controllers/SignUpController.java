package controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.dto.UserDto;
import services.UserService;
import utils.AlertUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private CheckBox chkAgree;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private PasswordField pwdConfirmPassword;
    @FXML
    private RadioButton rButton1, rButton2;
    @FXML
    private ToggleGroup tgGender;
    @FXML
    private ChoiceBox<String> roleBox;

    private String[] roles = {"admin", "user"};

    @FXML
    private void handleSignUp(ActionEvent ae) throws IOException {
        // Validate fields
        if (isAnyFieldEmpty()) {
            AlertUtil.showErrorAlert("Form Error", "Empty Fields", "Please fill in all fields.");
            return;
        }

        // Get the gender text ("male" or "female") and map it to "M" or "F"
        String gender = ((RadioButton) tgGender.getSelectedToggle()).getText();
        String genderCode = gender.equalsIgnoreCase("male") ? "M" : "F";

        // Get the selected role
        String role = roleBox.getValue();

        // Check if the terms and conditions are agreed
        if (!chkAgree.isSelected()) {
            AlertUtil.showErrorAlert("Terms and Conditions", "Agreement Required", "You must agree with the terms and conditions.");
            return;
        }

        UserDto userSignUpData = new UserDto(
                this.txtFirstName.getText(),
                this.txtLastName.getText(),
                genderCode,
                role,
                this.txtEmail.getText(),
                this.pwdPassword.getText(),
                this.pwdConfirmPassword.getText()
        );

        boolean response = UserService.signUp(userSignUpData);
        if (response) {
            System.out.println("User created");
            AlertUtil.showSuccessAlert(
                    "User Creation Success",
                    "Account Created",
                    "The user account has been successfully created."
            );
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
        } else {
            System.out.println("User creation failed");
            AlertUtil.showErrorAlert(
                    "User Creation Failed",
                    "Creation Error",
                    "There was an error creating the user account. Please try again."
            );
        }
    }

    @FXML
    private void handleCancel(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleBox.getItems().addAll(roles);
        tgGender = new ToggleGroup();
        this.rButton1.setToggleGroup(tgGender);
        this.rButton2.setToggleGroup(tgGender);
    }

    @FXML
    public void handelChangeLanguage(ActionEvent ae) {
        Navigator.changeLanguage();
        Navigator.navigate(ae, Navigator.CREATE_ACCOUNT_PAGE);
    }

    private boolean isAnyFieldEmpty() {
        return txtFirstName.getText().isEmpty() ||
                txtLastName.getText().isEmpty() ||
                txtEmail.getText().isEmpty() ||
                pwdPassword.getText().isEmpty() ||
                pwdConfirmPassword.getText().isEmpty() ||
                tgGender.getSelectedToggle() == null ||
                roleBox.getValue() == null;
    }
}
