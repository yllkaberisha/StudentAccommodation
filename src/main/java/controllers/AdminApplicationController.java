package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Student;
import services.AdminApplicationService;

public class AdminApplicationController {

        @FXML
        private TextField txtName;
        @FXML
        private TextField txtMobile;
        @FXML
        private TextField txtCourse;
        @FXML
        private TextField txtName1;
        @FXML
        private TextField txtName2;
        @FXML
        private TextField txtName3;

        @FXML
        private TableView<Student> table;
        @FXML
        private TableColumn<Student, String> FirstNameColmn;
        @FXML
        private TableColumn<Student, String> LastNameColmn;
        @FXML
        private TableColumn<Student, String> GenderColmn;
        @FXML
        private TableColumn<Student, String> FacultyColmn;
        @FXML
        private TableColumn<Student, String> MajorColmn;
        @FXML
        private TableColumn<Student, String> AverageGadeColmn;
        @FXML
        private TableColumn<Student, String> StatusColmn;
        @FXML
        private TableColumn<Student, String> YearColmn;
        @FXML
        private TableColumn<Student, String> UserIdColmn;

        @FXML
        private Button btnAdd;
        @FXML
        private Button btnUpdate;
        @FXML
        private Button btnDelete;

        private ObservableList<Student> studentList;
        private final AdminApplicationService service = new AdminApplicationService() ;

        @FXML
        public void initialize() {
                studentList = FXCollections.observableArrayList(service.getAllStudents());

                // Set up the table columns
                FirstNameColmn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                LastNameColmn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                GenderColmn.setCellValueFactory(new PropertyValueFactory<>("gender"));
                FacultyColmn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
                MajorColmn.setCellValueFactory(new PropertyValueFactory<>("major"));
                AverageGadeColmn.setCellValueFactory(new PropertyValueFactory<>("averageGrade"));
                StatusColmn.setCellValueFactory(new PropertyValueFactory<>("status"));
                YearColmn.setCellValueFactory(new PropertyValueFactory<>("year"));
                UserIdColmn.setCellValueFactory(new PropertyValueFactory<>("userId"));

                table.setItems(studentList);
        }

        @FXML
        private void handleAdd() {
                Student student = new Student(txtName.getText(), txtName1.getText(), "Gender", txtName2.getText(), txtMobile.getText(), txtCourse.getText(), "Status", txtName3.getText(), "UserId");
                service.addStudent(student);
                studentList.add(student);
                clearFields();
        }

        @FXML
        private void handleUpdate() {
                Student selectedStudent = table.getSelectionModel().getSelectedItem();
                if (selectedStudent != null) {
                        selectedStudent.setFirstName(txtName.getText());
                        selectedStudent.setLastName(txtName1.getText());
                        selectedStudent.setGender("Gender");
                        selectedStudent.setFaculty(txtName2.getText());
                        selectedStudent.setMajor(txtMobile.getText());
                        selectedStudent.setAverageGrade(txtCourse.getText());
                        selectedStudent.setStatus("Status");
                        selectedStudent.setYear(txtName3.getText());
                        selectedStudent.setUserId("UserId");
                        service.updateStudent(selectedStudent);
                        table.refresh();
                        clearFields();
                }
        }

        @FXML
        private void handleDelete() {
                Student selectedStudent = table.getSelectionModel().getSelectedItem();
                if (selectedStudent != null) {
                        service.deleteStudent(selectedStudent);
                        studentList.remove(selectedStudent);
                        clearFields();
                }
        }

        private void clearFields() {
                txtName.clear();
                txtName1.clear();
                txtName2.clear();
                txtMobile.clear();
                txtCourse.clear();
                txtName3.clear();
        }

        @FXML
        public void handleClear(ActionEvent actionEvent) {
                clearFields();
        }
}
