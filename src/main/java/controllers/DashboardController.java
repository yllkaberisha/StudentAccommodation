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
    private Label lblGenderDistribution;
    @FXML
    private Label lblMaleUsers;
    @FXML
    private Label lblFemaleUsers;
    @FXML
    private Label lblTotalUsers;
    @FXML
    private Label totalUsersLabel;
    @FXML
    private Label totalMaleUsersLabel;
    @FXML
    private Label totalFemaleUsersLabel;
    @FXML
    private PieChart genderPieChart;
    @FXML
    private PieChart dormPieChart;
    @FXML
    private Label lblDormDistribution;
    @FXML
    private Label lblMaleDorms;
    @FXML
    private Label lblFemaleDorms;
    @FXML
    private Label lblTotalDorms;
    @FXML
    private Label totalDormLabel;
    @FXML
    private Label maleDormCountLabel;
    @FXML
    private Label lblTotalRooms;
    @FXML
    private Label totalDormCountLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set localized texts
        lblGenderDistribution.setText(resourceBundle.getString("lblGenderDistribution"));
        lblMaleUsers.setText(resourceBundle.getString("lblMaleUsers"));
        lblFemaleUsers.setText(resourceBundle.getString("lblFemaleUsers"));
        lblTotalUsers.setText(resourceBundle.getString("lblTotalUsers"));
        lblDormDistribution.setText(resourceBundle.getString("lblDormDistribution"));
        lblMaleDorms.setText(resourceBundle.getString("lblMaleDorms"));
        lblFemaleDorms.setText(resourceBundle.getString("lblFemaleDorms"));
        lblTotalDorms.setText(resourceBundle.getString("lblTotalDorms"));

        // Load statistics
        loadUserStatistics();
        loadDormStatistics();
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

    private void loadDormStatistics() {
        long totalMaleCapacity = dormService.getTotalCapacityForMaleRooms();
        long totalFemaleCapacity = dormService.getTotalCapacityForFemaleRooms();
        long totalCapacity = dormService.getTotalCapacity();

        totalRoomsCountLabel.setText(String.valueOf(totalCapacity));

        updateDormPieChart(totalMaleCapacity, totalFemaleCapacity);
    }

    private void updateGenderPieChart(long maleCount, long femaleCount) {
        PieChart.Data maleData = new PieChart.Data("Male", maleCount);
        PieChart.Data femaleData = new PieChart.Data("Female", femaleCount);
        genderPieChart.getData().clear();
        genderPieChart.getData().addAll(maleData, femaleData);
    }

    private void updateDormPieChart(int maleDormCount, int femaleDormCount) {
        PieChart.Data maleDormData = new PieChart.Data("Male", maleDormCount);
        PieChart.Data femaleDormData = new PieChart.Data("Female", femaleDormCount);
        dormPieChart.getData().clear();
        dormPieChart.getData().addAll(maleDormData, femaleDormData);
    }
}
