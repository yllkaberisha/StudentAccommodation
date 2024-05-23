package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminRoomsController {

        @FXML
        private TableColumn<?, ?> ApplicationIDColmn;

        @FXML
        private TableColumn<?, ?> RoomColmn;

        @FXML
        private TableColumn<?, ?> UserIdColmn;

        @FXML
        private Button btnAdd;

        @FXML
        private Button btnUpdate;

        @FXML
        private ChoiceBox<?> statusBox;

        @FXML
        private TableView<?> table;

        @FXML
        private TextField txtRoomID;

        @FXML
        void handleAdd(ActionEvent event) {

        }

        @FXML
        void handleUpdate(ActionEvent event) {

        }
}
