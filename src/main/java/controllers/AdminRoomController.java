package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import models.dto.AdminRoomDto;
import services.AdminRoomService;
import utils.AlertUtil;

import java.util.List;

public class AdminRoomController {


    @FXML
    private TextField txtCapacity;

    @FXML
    private TextField txtFloor;

    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    private TableView<AdminRoomDto> table;

    @FXML
    private TableColumn<AdminRoomDto, String> RoomsIDColmn;

    @FXML
    private TableColumn<AdminRoomDto, String> TypeColmn;

    @FXML
    private TableColumn<AdminRoomDto, String> RoomsCapacityColmn;

    @FXML
    private TableColumn<AdminRoomDto, String> FloorColmn;

    private AdminRoomService service = new AdminRoomService();

    @FXML
    public void initialize() {
        // Set up the columns in the table
        RoomsIDColmn.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        TypeColmn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        RoomsCapacityColmn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        FloorColmn.setCellValueFactory(new PropertyValueFactory<>("floor"));

        // Load data into the table
        List<AdminRoomDto> roomList = service.getAllRooms();
        loadTableData(roomList);

        // Add listener for row selection
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> populateTextFields(newValue)
        );

        // Populate statusBox with options
        typeBox.setItems(FXCollections.observableArrayList("M", "F"));
    }

    private void loadTableData(List<AdminRoomDto> roomList) {
        // Fetch data from the service
        ObservableList<AdminRoomDto> rooms = FXCollections.observableArrayList(roomList);
        table.setItems(rooms);
    }

    private void populateTextFields(AdminRoomDto room) {
        if (room != null) {
            txtCapacity.setText(room.getCapacity());
            txtFloor.setText(room.getFloor());
            typeBox.setValue(room.getRoomType());
        }
    }

    @FXML
    private void handleAdd(ActionEvent event) {
        String type = typeBox.getValue();
        String capacity = txtCapacity.getText();
        String floor = txtFloor.getText();

        AdminRoomDto newRoom = new AdminRoomDto(null, type, capacity, floor);
        service.addRoom(newRoom);
        loadTableData(service.getAllRooms());
        AlertUtil.showSuccessAlert("Success", "Room Added", "The room has been added successfully.");
        clearFields();
    }

    private void clearFields() {
        txtCapacity.clear();
        txtFloor.clear();
        typeBox.setValue(null);
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        AdminRoomDto selectedRoom = table.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
//            selectedRoom.setRoomID(txtRoomID.getText());
            selectedRoom.setRoomType(typeBox.getValue());
            selectedRoom.setCapacity(txtCapacity.getText());
            selectedRoom.setFloor(txtFloor.getText());

            service.updateRoom(selectedRoom);
            table.refresh();
            AlertUtil.showSuccessAlert("Success", "Room Updated", "The room has been updated successfully.");
            clearFields();
        } else {
            AlertUtil.showWarningAlert("No Selection", "No Room Selected", "Please select a room in the table.");
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        AdminRoomDto selectedRoom = table.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            service.deleteRoom(selectedRoom.getRoomID());
            table.getItems().remove(selectedRoom);
            AlertUtil.showSuccessAlert("Success", "Room Deleted", "The room has been deleted successfully.");
        } else {
            AlertUtil.showWarningAlert("No Selection", "No Room Selected", "Please select a room in the table.");
        }
    }
}
