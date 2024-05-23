package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.AdminUser;
import models.filter.AdminUserFilter;
import services.AdminUserService;
import utils.AlertUtil;

import java.util.List;

public class AdminUsersController {

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtRoom;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtRole;

    @FXML
    private TableView<AdminUser> tableView;

    @FXML
    private TableColumn<AdminUser, String> IDCol;

    @FXML
    private TableColumn<AdminUser, String> FirstNameCol;

    @FXML
    private TableColumn<AdminUser, String> LastNameCol;

    @FXML
    private TableColumn<AdminUser, String> GenderCol;

    @FXML
    private TableColumn<AdminUser, String> EmailCol;

    @FXML
    private TableColumn<AdminUser, String> RoleCol;

    @FXML
    private TableColumn<AdminUser, String> RoomCol;

    private AdminUserService service = new AdminUserService();

    @FXML
    public void initialize() {
        // Set up the columns in the table
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        RoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        RoomCol.setCellValueFactory(new PropertyValueFactory<>("room"));

        // Load data into the table
        List<AdminUser> usersList = service.getAllUsers();
        loadTableData(usersList);

        // Add listener for row selection
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populateTextFields(newValue)
        );
    }

    private void loadTableData(List<AdminUser> usersList) {
        // Fetch data from the service
        ObservableList<AdminUser> users = FXCollections.observableArrayList(usersList);
        tableView.setItems(users);
    }

    private void populateTextFields(AdminUser user) {
        if (user != null) {
            txtID.setText(user.getId());
            txtFirstName.setText(user.getFirstName());
            txtLastName.setText(user.getLastName());
            txtEmail.setText(user.getEmail());
            txtRoom.setText(user.getRoom());
            txtGender.setText(user.getGender());
            txtRole.setText(user.getRole());
        }
    }

    @FXML
    private void handleFilterClick(ActionEvent event) {
        String id = txtID.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        String room = txtRoom.getText();
        String gender = txtGender.getText();
        String role = txtRole.getText();

        AdminUserFilter filter = new AdminUserFilter(id, firstName, lastName, email, room, gender, role);
        List<AdminUser> filteredUsers = service.getByFilter(filter);
        loadTableData(filteredUsers);
    }

    @FXML
    private void handleDeleteClick(ActionEvent actionEvent) {
        AdminUser selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            service.deleteUser(selectedUser);
            tableView.getItems().remove(selectedUser);
            AlertUtil.showSuccessAlert("Success", "User Deleted", "The user has been deleted successfully.");
        } else {
            AlertUtil.showWarningAlert("No Selection", "No User Selected", "Please select a user in the table.");
        }
    }

    @FXML
    private void handleUpdateClick(ActionEvent actionEvent) {
        AdminUser selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            selectedUser.setFirstName(txtFirstName.getText());
            selectedUser.setLastName(txtLastName.getText());
            selectedUser.setEmail(txtEmail.getText());
            selectedUser.setRoom(txtRoom.getText());
            selectedUser.setGender(txtGender.getText());
            selectedUser.setRole(txtRole.getText());

            service.updateUser(selectedUser);
            tableView.refresh();
            AlertUtil.showSuccessAlert("Success", "User Updated", "The user has been updated successfully.");
        } else {
            AlertUtil.showWarningAlert("No Selection", "No User Selected", "Please select a user in the table.");
        }
    }

    @FXML
    private void handleClearClick(ActionEvent actionEvent) {
        txtID.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtEmail.clear();
        txtRoom.clear();
        txtGender.clear();
        txtRole.clear();

        List<AdminUser> usersList = service.getAllUsers();
        loadTableData(usersList);
//        AlertUtil.showSuccessAlert("Success", "Fields Cleared", "All input fields have been cleared.");
    }
}
