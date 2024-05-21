package controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.dto.UserDto;
import services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
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
        // Get the gender text ("male" or "female") and map it to "M" or "F"
        String gender = ((RadioButton) tgGender.getSelectedToggle()).getText();
        String genderCode = gender.equalsIgnoreCase("male") ? "M" : "F";

        // Get the selected role
        String role = roleBox.getValue();

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
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
        } else {
            System.out.println("User creation failed");
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
        Navigator.navigate(ae,Navigator.CREATE_ACCOUNT_PAGE);
    }
}
