package controllers;

import app.Navigator;
import app.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.dto.ApplicationDto;
import services.UserService;
import utils.AlertUtil;

public class StudentApplicationController {
    public Label statusLabelText;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField txtFaculty;
    @FXML
    private TextField txtYearsOfStudies;
    @FXML
    private TextField txtMajor;
    @FXML
    private TextField txtAverageGrade;
    @FXML
    private Button btnApply;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnUpdate;

    private int applicationId = -1;

    // No-argument constructor
    public StudentApplicationController() {
    }

    @FXML
    public void initialize() {
        // Add listeners to enforce numeric input
        addNumericValidation(txtYearsOfStudies);
        addDecimalValidation(txtAverageGrade);

        // Check if an application exists for the current user
        int userId = SessionManager.getUser().getID();
        ApplicationDto application = UserService.getApplicationByUserId(userId);

        if (application != null) {
            applicationId = application.getID();
            txtFaculty.setText(application.getFaculty());
            txtYearsOfStudies.setText(application.getYearsOfStudies().toString());
            txtMajor.setText(application.getMajor());
            txtAverageGrade.setText(String.valueOf(application.getAverageGrade()));

            setStatus(application.getStatus());
        } else {
            setStatus("No Application");
        }
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        if (applicationId != -1) {
            AlertUtil.showErrorAlert("Application Exists", "You have already applied.", "");
            return;
        }

        int userID = SessionManager.getUser().getID();
        String faculty = txtFaculty.getText();
        Integer yearsOfStudies = Integer.parseInt(txtYearsOfStudies.getText());
        String major = txtMajor.getText();
        double averageGrade = Double.parseDouble(txtAverageGrade.getText());

        ApplicationDto applicationData = new ApplicationDto(applicationId, faculty, yearsOfStudies, major, averageGrade, null, userID);
        boolean saved = UserService.saveInformation(applicationData);

        if (saved) {
            AlertUtil.showSuccessAlert("Success", "Application saved successfully!", "");
            initialize();
        } else {
            AlertUtil.showErrorAlert("Error", "Failed to save application.", "");
        }
    }

    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        if (applicationId != -1) {
            AlertUtil.showErrorAlert("Application Exists", "You have already applied.", "");
            return;
        }
        resetFormFields();
    }

    @FXML
    public void handleUpdate(ActionEvent actionEvent) {
        if (applicationId == -1) {
            AlertUtil.showErrorAlert("No Application", "Please apply before updating.", "");
            return;
        }
        String status = statusLabel.getText();
        if (!"pending".equalsIgnoreCase(status)) {
            AlertUtil.showErrorAlert("Update Not Allowed", "You can only update an application with 'Pending' status.", "");
            return;
        }

        int userID = SessionManager.getUser().getID();
        String faculty = txtFaculty.getText();
        Integer yearsOfStudies = Integer.parseInt(txtYearsOfStudies.getText());
        String major = txtMajor.getText();
        double averageGrade = Double.parseDouble(txtAverageGrade.getText());


        ApplicationDto applicationData = new ApplicationDto(applicationId, faculty, yearsOfStudies, major, averageGrade, status, userID);
        boolean updated = UserService.updateInformation(applicationData);

        if (updated) {
            AlertUtil.showSuccessAlert("Success", "Application updated successfully!", "");
            initialize();
        } else {
            AlertUtil.showErrorAlert("Error", "Failed to update application.", "");
        }
    }

    private void setStatus(String status) {
        statusLabel.setText(status);
    }

    private void resetFormFields() {
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

    @FXML
    public void handleLogOut(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.LOGIN_PAGE);
    }

    @FXML
    public void handelChangeLanguage(ActionEvent ae) {
        Navigator.changeLanguage();
        Navigator.navigate(ae,Navigator.STUDENT_APPLICATION_PAGE);
    }
}
