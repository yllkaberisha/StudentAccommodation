module com.example.studentaccommodation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports app;
    opens controllers to javafx.fxml;
    opens models to javafx.base;
    exports models.dto;
    opens models.filter to javafx.base;
}