module com.example.studentaccommodation {
    requires javafx.controls;
    requires javafx.fxml;


    exports app;
    opens controllers to javafx.fxml;
}