package controllers;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SideBarController {

    @FXML
    private BorderPane bp;

    @FXML
    private VBox vb;

    @FXML
    private AnchorPane ap;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button usersButton;

    @FXML
    private Button roomsButton;

    @FXML
    private Button applicationsButton;

    @FXML
    private Button languageButton;

    @FXML
    private Button logOutButton;

    @FXML
    public void initialize() {
        // Any initialization logic can go here
    }

    // Event handler methods
    @FXML
    private void dashboardPage(MouseEvent event) {
        System.out.println("Dashboard button clicked");
        // Use Navigator to switch to the dashboard pag
        Navigator.navigate(event,Navigator.DASHBOARD_PAGE); // Update with actual dashboard page path
//        bp.setCenter(ap);
    }

    @FXML
    private void usersPageClick(MouseEvent event) {
        System.out.println("Users button clicked");
        loadPage(Navigator.ADMIN_USERS);
    }

    @FXML
    private void roomsPageClick(MouseEvent event) {
        System.out.println("Rooms button clicked");
        loadPage(Navigator.ADMIN_ROOMS);
    }

    @FXML
    private void applicationsPageClick(MouseEvent event) {
        System.out.println("Applications button clicked");
        loadPage(Navigator.ADMIN_APPLICATIONS);
    }

    @FXML
    private void logOutClick(MouseEvent event) {
        System.out.println("Log Out button clicked");
        bp.setLeft(null);
        bp.setCenter(null);
        loadPage(Navigator.LOGIN_PAGE);
    }
    public void changeLanguageClick(MouseEvent event) {
        Navigator.changeLanguage();
        Navigator.navigate(event, Navigator.ADMIN_MAIN);
//        loadPage(Navigator.ADMIN_MAIN);
    }

    // Helper method to load pages into the center of the BorderPane
    private void loadPage(String page) {
        Pane view = Navigator.loadPane(page);
        if (view != null) {
            bp.setCenter(view);
        } else {
            System.err.println("Error loading page: " + page);
        }
    }


}