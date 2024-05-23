package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Student;
import models.dto.AdminApplicationDto;
import services.AdminApplicationService;
import utils.AlertUtil;

import java.util.List;

public class AdminApplicationController {

        @FXML
        private TextField txtRoomID;

        @FXML
        private ChoiceBox<String> statusBox;

        @FXML
        private TableView<Student> table;

        @FXML
        private TableColumn<Student, Integer> UserIdColmn;

        @FXML
        private TableColumn<Student, String> FirstNameColmn;

        @FXML
        private TableColumn<Student, String> LastNameColmn;

        @FXML
        private TableColumn<Student, String> GenderColmn;

        @FXML
        private TableColumn<Student, String> StatusColmn;

        @FXML
        private TableColumn<Student, String> RoomColmn;

        @FXML
        private TableColumn<Student, String> FacultyColmn;

        @FXML
        private TableColumn<Student, String> MajorColmn;

        @FXML
        private TableColumn<Student, Double> AverageGadeColmn;

        @FXML
        private TableColumn<Student, Integer> YearColmn;

        @FXML
        private TableColumn<Student, Integer> ApplicationIDColmn;

        @FXML
        private Button btnAdd;

        @FXML
        private Button btnUpdate;

        private AdminApplicationService service = new AdminApplicationService();

        private Student selectedStudent;

        @FXML
        private void initialize() {
                // Initialize the table columns
                UserIdColmn.setCellValueFactory(new PropertyValueFactory<>("userId"));
                FirstNameColmn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                LastNameColmn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                GenderColmn.setCellValueFactory(new PropertyValueFactory<>("gender"));
                StatusColmn.setCellValueFactory(new PropertyValueFactory<>("status"));
                RoomColmn.setCellValueFactory(new PropertyValueFactory<>("room"));
                FacultyColmn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
                MajorColmn.setCellValueFactory(new PropertyValueFactory<>("major"));
                AverageGadeColmn.setCellValueFactory(new PropertyValueFactory<>("averageGrade"));
                YearColmn.setCellValueFactory(new PropertyValueFactory<>("year"));
                ApplicationIDColmn.setCellValueFactory(new PropertyValueFactory<>("applicationID"));

                // Add initial data to the statusBox
                statusBox.getItems().addAll("pending", "approved", "declined");

                List<Student> applicationList = service.getAllApplications();
                loadTableData(applicationList);

                // Add listener for row selection
                table.getSelectionModel().selectedItemProperty().addListener(
                        (observable, oldValue, newValue) -> {
                                selectedStudent = newValue;
                                populateTextFields(newValue);
                        }
                );
        }

        private void populateTextFields(Student std) {
                if (std != null) {
                        txtRoomID.setText(String.valueOf(std.getRoom())); // Convert int to String and set it to TextField
                        statusBox.setValue(std.getStatus()); // Set the status to ChoiceBox
                }
        }

        private void loadTableData(List<Student> usersList) {
                // Fetch data from the service
                ObservableList<Student> users = FXCollections.observableArrayList(usersList);
                table.setItems(users);
        }

        @FXML
        private void handleAdd(ActionEvent event) {
                if (selectedStudent == null) {
                        AlertUtil.showErrorAlert("No student selected", "Please select a student.", "");
                        return;
                }

                int roomID = Integer.parseInt(txtRoomID.getText());
                String status = statusBox.getValue();
                int applicationID = selectedStudent.getApplicationID();

                AdminApplicationDto newAdminAppDto = new AdminApplicationDto(roomID, status, applicationID);

                if (service.allocationExists(applicationID)) {
                        AlertUtil.showErrorAlert("Allocation Exists", "An allocation for this application ID already exists. Use update instead.", "");
                } else {
                        service.addAllocation(newAdminAppDto);
                        AlertUtil.showSuccessAlert("Success", "Allocation inserted", "The allocation has been successfully inserted.");
                        // Optionally refresh the table data
                        List<Student> applicationList = service.getAllApplications();
                        loadTableData(applicationList);
                }
        }

        @FXML
        private void handleUpdate(ActionEvent event) {
                if (selectedStudent == null) {
                        AlertUtil.showErrorAlert("No student selected", "Please select a student.", "");
                        return;
                }

                int roomID = Integer.parseInt(txtRoomID.getText());
                String status = statusBox.getValue();
                int applicationID = selectedStudent.getApplicationID();

                if (!service.allocationExists(applicationID)) {
                        AlertUtil.showErrorAlert("No Allocation Exists", "No allocation exists for this application ID. Please add an allocation first.", "");
                        return;
                }

                AdminApplicationDto newAdminAppDto = new AdminApplicationDto(roomID, status, applicationID);

                service.updateAllocation(newAdminAppDto);
                AlertUtil.showSuccessAlert("Success", "Allocation updated", "The allocation has been successfully updated.");

                // Optionally refresh the table data
                List<Student> applicationList = service.getAllApplications();
                loadTableData(applicationList);
        }


}
