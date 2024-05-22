package controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.dto.ApplicationDto;
import services.UserService;

public class StudentApplicationController {
//    @FXML
//    private Label statusLabel;
    @FXML
    private TextField txtFaculty;
    @FXML
    private TextField txtYearsOfStudies;
    @FXML
    private TextField txtMajor;
    @FXML
    private TextField txtAverageGrade;
    private int applicationId;
//    @FXML
//    private Button saveButton;
//
//    @FXML
//    private Button cancelButton;

    // No-argument constructor
    public StudentApplicationController() {
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        String faculty = txtFaculty.getText();
        Integer yearsOfStudies = Integer.parseInt(txtYearsOfStudies.getText());
        String major = txtMajor.getText();
        double averageGrade = Double.parseDouble(txtAverageGrade.getText());

        // Krijo një instance të ApplicationDto
        ApplicationDto applicationData = new ApplicationDto(faculty, yearsOfStudies, major, averageGrade);

        // Thirr funksionin për të ruajtur aplikacionin në bazën e të dhënave
        boolean saved = UserService.saveInformation(applicationData);

        if (saved) {
            System.out.println("Application saved successfully!");
            // Pastro fushat e formës

            resetFormFields();
//            updateButtonVisibility("Pending");
//            setStatus("Pending");
        } else {
            System.out.println("Failed to save application.");
        }

    }
//    public void updateButtonVisibility(String status) {
//        if ("Pending".equalsIgnoreCase(status)) {
//            // Trego butonat nëse statusi është 'Pending'
//            saveButton.setVisible(true);
//            cancelButton.setVisible(true);
//        } else {
//            // Fshij butonat nëse statusi është tjetër
//            saveButton.setVisible(false);
//            cancelButton.setVisible(false);
//        }
//    }

    // Metoda për të vendosur statusin në label
//    public void setStatus(String status) {
//        statusLabel.setText("Status: " + status);
//    }

    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        System.out.println("Cancel button clicked, resetting form fields."); // Debug statement
        resetFormFields();
//        updateButtonVisibility("Pending");
//        setStatus("Pending");
    }
    @FXML
    public void initialize() {
        // Add listeners to enforce numeric input
        addNumericValidation(txtYearsOfStudies);
        addDecimalValidation(txtAverageGrade);
    }
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
    public void handleUpdate(ActionEvent actionEvent) {
        String faculty = txtFaculty.getText();
        Integer yearsOfStudies = Integer.parseInt(txtYearsOfStudies.getText());
        String major = txtMajor.getText();
        double averageGrade = Double.parseDouble(txtAverageGrade.getText());
        if (applicationId != -1) {
            ApplicationDto applicationData = new ApplicationDto(faculty, yearsOfStudies, major, averageGrade);

            boolean updated = UserService.updateInformation(applicationData);

            if (updated) {
                System.out.println("Application updated successfully!");
                resetFormFields();
//            updateButtonVisibility("Pending");
//            setStatus("Pending");
            } else {
                System.out.println("Failed to update application.");
            }
        }
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
        Navigator.navigate(actionEvent,Navigator.LOGIN_PAGE);
    }
    private void resetFormFields() {
        System.out.println("Resetting form fields."); // Debug statement
        txtFaculty.clear();
        txtYearsOfStudies.clear();
        txtMajor.clear();
        txtAverageGrade.clear();
    }
    private void addNumericValidation(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    private void addDecimalValidation(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                textField.setText(oldValue);
            }
        });
    }

}
