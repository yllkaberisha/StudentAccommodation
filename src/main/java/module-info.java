module com.example.studentaccommodation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.studentaccommodation to javafx.fxml;
    exports com.example.studentaccommodation;
}