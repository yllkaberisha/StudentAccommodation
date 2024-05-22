package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class AdminApplicationController implements Initializable {

        @FXML
        private TableColumn<?, ?> AverageGadeColmn;

        @FXML
        private TableColumn<?, ?> FacultyColmn;

        @FXML
        private TableColumn<?, ?> FirstNameColmn;

        @FXML
        private TableColumn<?, ?> GenderColmn;

        @FXML
        private TableColumn<?, ?> LastNameColmn;

        @FXML
        private TableColumn<?, ?> MajorColmn;

        @FXML
        private TableColumn<?, ?> StatusColmn;

        @FXML
        private TableColumn<?, ?> UserIdColmn;

        @FXML
        private TableColumn<?, ?> YearColmn;

        @FXML
        private Button btnAdd;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnUpdate;

        @FXML
        private TableView<?> table;

        @FXML
        private TextField txtCourse;

        @FXML
        private TextField txtMobile;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtName1;

        @FXML
        private TextField txtName2;

        @FXML
        private TextField txtName3;

        @FXML
        void handleAdd(ActionEvent event) {

        }

        @FXML
        void handleDelete(ActionEvent event) {

        }

        @FXML
        void handleUpdate(ActionEvent event) {

        }


        @Override
        public void initialize(URL url, ResourceBundle rb) {

        }
}
