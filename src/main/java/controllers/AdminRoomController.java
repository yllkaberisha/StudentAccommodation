package controllers;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.AdminUser;
import services.AdminRoomService;
import models.dto.AdminRoomDto; // Import AdminRoomDto


import java.util.List;

public class AdminRoomController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label lblStudentRegistration;

    @FXML
    private TextField txtRoomID;

    @FXML
    private ChoiceBox<String> statusBox;

    @FXML
    private TableView<AdminUser> table;

    @FXML
    private TableColumn<AdminUser, String> RoomsIDColmn;

    @FXML
    private TableColumn<AdminUser, String> TypeColmn;

    @FXML
    private TableColumn<AdminUser, String> RoomsCapacityColmn;

    @FXML
    private TableColumn<AdminUser, String>FloorColmn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    private AdminRoomService roomService;

    @FXML
    public void initialize() {
        roomService = new AdminRoomService();
        loadRoomData();

//        // Initialize table columns
//        RoomsIDColmn.setCellValueFactory(new PropertyValueFactory<>("roomID"));
//        TypeColmn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
//        RoomsCapacityColmn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
//        FloorColmn.setCellValueFactory(new PropertyValueFactory<>("floor"));


        // Add initial data to the statusBox
//        List<String> roomTypes = roomService.getDistinctRoomTypes();
//        if (roomTypes != null && !roomTypes.isEmpty()) {
//            statusBox.getItems().addAll(roomTypes);
//            statusBox.getSelectionModel().selectFirst(); // Select the first item by default
//        }

        statusBox.getItems().addAll("1", "2");


//        // Add listener for row selection
//        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                txtRoomID.setText(newValue.getRoomID());
//                statusBox.setValue(newValue.getRoomType());
//                // Populate other fields as needed
//            }
//        });
    }
//
//    @FXML
//    public void handleAdd() {
//        String roomID = txtRoomID.getText();
//        String roomType = roomTypeBox.getValue();
//        int capacity = 0; // Adjust according to your data source
//        int floor = 0; // Adjust according to your data source
//        if (roomID != null && !roomID.isEmpty() && roomType != null) {
//            roomService.addRoom(roomID, roomType, capacity, floor);
//            loadRoomData();
//        } else {
//            showError("Please fill in all fields.");
//        }
//    }

//    @FXML
//    public void handleUpdate() {
//        AdminUser selectedRoom = table.getSelectionModel().getSelectedItem();
//        if (selectedRoom != null) {
//            String roomID = selectedRoom.getRoomID();
//            String roomType = roomTypeBox.getValue();
//            int capacity = 0; // Adjust according to your data source
//            int floor = 0; // Adjust according to your data source
//            if (roomType != null) {
//                roomService.updateRoom(roomID, roomType, capacity, floor);
//                loadRoomData();
//            } else {
//                showError("Please select a room type.");
//            }
//        } else {
//            showError("Please select a room to update.");
//        }
//    }
//
//    @FXML
//    public void handleDelete() {
//        AdminUser selectedRoom = table.getSelectionModel().getSelectedItem();
//        if (selectedRoom != null) {
//            String roomID = selectedRoom.getRoomID();
//            roomService.deleteRoom(roomID);
//            loadRoomData();
//        } else {
//            showError("Please select a room to delete.");
//        }
//    }

//
//    @FXML
//    public void handleAdd() {
//        String roomID = txtRoomID.getText();
//        String roomType = statusBox.getValue();
//        String capacity = ""; // Adjust according to your data source
//        String floor = ""; // Adjust according to your data source
//        if (roomID != null && !roomID.isEmpty() && roomType != null) {
//            roomService.addRoom(roomID, roomType, capacity, floor);
//            loadRoomData();
//        } else {
//            showError("Please fill in all fields.");
//        }
//    }

    @FXML
    public void handleAdd() {
        String roomID = txtRoomID.getText();
        String roomType = statusBox.getValue();
        String capacity = ""; // Adjust according to your data source
        String floor = ""; // Adjust according to your data source
        if (roomID != null && !roomID.isEmpty() && roomType != null) {
            AdminRoomDto newRoomDto = new AdminRoomDto(roomID, roomType, capacity, floor);
            roomService.addRoom(newRoomDto);
            loadRoomData();
        } else {
            showError("Please fill in all fields.");
        }
    }





//    @FXML
//    public void handleUpdate() {
//        AdminUser selectedRoom = table.getSelectionModel().getSelectedItem();
//        if (selectedRoom != null) {
//            String roomID = selectedRoom.getRoomID();
//            String roomType = statusBox.getValue();
//            String capacity = ""; // Adjust according to your data source
//            String floor = ""; // Adjust according to your data source
//            if (roomType != null) {
//                roomService.updateRoom(roomID, roomType, capacity, floor);
//                loadRoomData();
//            } else {
//                showError("Please select a room type.");
//            }
//        } else {
//            showError("Please select a room to update.");
//        }
//    }
@FXML
public void handleUpdate() {
    AdminUser selectedRoom = table.getSelectionModel().getSelectedItem();
    if (selectedRoom != null) {
        String roomID = selectedRoom.getRoomID();
        String roomType = statusBox.getValue();
        String capacity = ""; // Adjust according to your data source
        String floor = ""; // Adjust according to your data source
        if (roomType != null) {
            AdminRoomDto RoomDto = new AdminRoomDto(roomID, roomType, capacity, floor);
            roomService.updateRoom(RoomDto);
            loadRoomData();
        } else {
            showError("Please select a room type.");
        }
    } else {
        showError("Please select a room to update.");
    }
}

//    @FXML
//    public void handleDelete() {
//        AdminUser selectedRoom = table.getSelectionModel().getSelectedItem();
//        if (selectedRoom != null) {
//            String roomID = selectedRoom.getRoomID();
//            roomService.deleteRoom(roomID);
//            loadRoomData();
//        } else {
//            showError("Please select a room to delete.");
//        }
//    }
@FXML
public void handleDelete() {
    AdminUser selectedRoom = table.getSelectionModel().getSelectedItem();
    if (selectedRoom != null) {
        String roomID = selectedRoom.getRoomID();
        roomService.deleteRoom(roomID);
        loadRoomData();
    } else {
        showError("Please select a room to delete.");
    }
}

    private void loadRoomData() {
        List<AdminUser> roomList = roomService.getAllRooms();
        // Populate table with room data
    }

    private void showError(String message) {
        // Implement logic to show error message to the user
    }
}
