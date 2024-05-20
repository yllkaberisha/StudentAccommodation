package controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import models.dto.UserDto;
import services.UserService;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
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
    private RadioButton rButton1, rButton2;
    @FXML
    private ToggleGroup tgGender;

@FXML
    public  void getGender(ActionEvent ae ){

        if(rButton1.isSelected()){
           txtGender.setText(rButton1.getText());
        }else if(rButton2.isSelected()){
            txtGender.setText(rButton2.getText());
        }

    }
    @FXML
    private ChoiceBox<String> roleBox;
    private String [] role = {"admin","user"};
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
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleBox.getItems().addAll(role);
        tgGender = new ToggleGroup();
        this.rButton1.setToggleGroup(tgGender);
        this.rButton2.setToggleGroup(tgGender);
//        roleBox.setOnAction(this::getRole);
    }
    @FXML
    public void initialize() {
        // Initialization code
        getGender();
    }

    public void getGender() {
        if (txtGender != null) {
            txtGender.setText("Your Gender Text");
        } else {
            System.out.println("txtGender is null");
        }
    }

//    @FXML
//    public void getRole(ActionEvent ae){
//        String role = roleBox.getValue();
//        txtRole.setText(role);
//    }


    // Vazhdoni për fushat e tjera të tekstit nëse është e nevojshme
    }

