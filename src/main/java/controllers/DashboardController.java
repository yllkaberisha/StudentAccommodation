package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import models.User;
import services.UserService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label totalUsersLabel;
    @FXML
    private Label totalMaleUsersLabel;
    @FXML
    private Label totalFemaleUsersLabel;
    @FXML
    private PieChart genderPieChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUserStatistics();
    }

    private void loadUserStatistics() {
        List<User> users = UserService.getAllUsers();
        int totalUsers = users.size();
        long totalMaleUsers = UserService.countMaleUsers(users);
        long totalFemaleUsers = UserService.countFemaleUsers(users);

        totalUsersLabel.setText(String.valueOf(totalUsers));
        totalMaleUsersLabel.setText(String.valueOf(totalMaleUsers));
        totalFemaleUsersLabel.setText(String.valueOf(totalFemaleUsers));

        updateGenderPieChart(totalMaleUsers, totalFemaleUsers);
    }

    private void updateGenderPieChart(long maleCount, long femaleCount) {
        PieChart.Data maleData = new PieChart.Data("Male", maleCount);
        PieChart.Data femaleData = new PieChart.Data("Female", femaleCount);
        genderPieChart.getData().clear();
        genderPieChart.getData().addAll(maleData, femaleData);
    }
}
