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

    public StudentApplicationController(TextField txtFaculty, TextField txtYearsOfStudies, TextField txtMajor, TextField txtAverageGrade) {
        this.txtFaculty = txtFaculty;
        this.txtYearsOfStudies = txtYearsOfStudies;
        this.txtMajor = txtMajor;
        this.txtAverageGrade = txtAverageGrade;
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
    @FXML
    public void  handleSave(ActionEvent ae){

    }
    @FXML
    public void  handleCancel(ActionEvent ae){

    }
    @FXML
    public void  handleUpdate(ActionEvent ae){

    }
}
