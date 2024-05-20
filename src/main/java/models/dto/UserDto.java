package models.dto;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;

public class UserDto {
    private String firstName;
    private String lastName;
    private String gender;
    private String role;
    private String email;
    private String password;
    private String confirmPassword;
    private RadioButton rButton1, rButton2;
    private ChoiceBox <String> roleBox;
    public UserDto(String firstName,String lastName,String gender,String role,String email,String password,String confirmPassword){
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.role=role;
        this.email=email;
        this.password=password;
        this.confirmPassword=confirmPassword;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

//    public RadioButton getrButton1() {
//        return rButton1;
//    }

//    public RadioButton getrButton2() {
//        return rButton2;
//    }
//
//    public ChoiceBox<String> getRoleBox() {
//        return roleBox;
//    }
}
