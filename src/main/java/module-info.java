module com.example.studentaccommodation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports app;
    opens controllers to javafx.fxml;
}