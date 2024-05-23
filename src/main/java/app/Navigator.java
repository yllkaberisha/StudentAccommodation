package app;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

import java.io.IOException;

public class Navigator {
    public final static  String LOGIN_PAGE = "login.fxml";
    public final static  String ADMIN_USERS = "admin_users.fxml";
    public final static  String ADMIN_ROOMS = "admin_rooms.fxml";
    public final static  String ADMIN_APPLICATIONS = "admin_applications.fxml";
    public final static  String CREATE_ACCOUNT_PAGE = "signup.fxml";
    public final static  String CHANGE_PASSWORD_ACCOUNT_PAGE = "changepassword.fxml";
    public static final String ADMIN_MAIN ="adminMain.fxml" ;
    public final static String DASHBOARD_PAGE = "dashboard.fxml";
    public final static String STUDENT_APPLICATION_PAGE = "student_application.fxml";
    private static ResourceBundle bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());


    public static void navigate(Event event, String form){

        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        navigate(stage, form);
    }


    public static void navigate(Stage stage, String form){
        Pane formPane = loadPane(form);
        Scene newScene = new Scene(formPane);
        String title = bundle.getString("txtStudentAccomodation");
        stage.setTitle(title);
        stage.setScene(newScene);
        stage.show();
    }

    public static void navigate(Pane pane, String form){
        Pane formPane = loadPane(form);
        pane.getChildren().clear();
        pane.getChildren().add(formPane);
    }

    public static void changeLanguage(){
        Locale degaultLocale = Locale.getDefault();
        if(degaultLocale.getLanguage().equals("en")){
            Locale.setDefault(
                    Locale.of("sq")
            );
        }
        else{
            Locale.setDefault(Locale.ENGLISH);
        }
        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
    }

    public static Pane loadPane(String form){
        FXMLLoader loader = new FXMLLoader(
                Navigator.class.getResource(form), bundle
        );
        try {
            return loader.load();
        }catch (IOException ioe){
            return null;
        }
    }
}