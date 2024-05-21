package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StudentApplicationController {
    @FXML
    private TextField txtFaculty;
    @FXML
    private TextField txtYearsOfStudies;
    @FXML
    private TextField txtMajor;
    @FXML
    private TextField txtAverageGrade;

    // No-argument constructor
    public StudentApplicationController() {
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        // Handle save logic here
    }

    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        // Handle cancel logic here
    }

    @FXML
    public void handleUpdate(ActionEvent actionEvent) {
        // Handle update logic here
    }

    public TextField getTxtFaculty() {
        return txtFaculty;
    }

    public TextField getTxtYearsOfStudies() {
        return txtYearsOfStudies;
    }

    public TextField getTxtMajor() {
        return txtMajor;
    }

    public TextField getTxtAverageGrade() {
        return txtAverageGrade;
    }

    public void handleLogOut(ActionEvent actionEvent) {
    }
}
