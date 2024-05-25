package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import models.User;
import services.DormService;
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
    private PieChart dormPieChart; // Add this line for the dorm pie chart
    @FXML
    private Label lblTotalRooms;
    @FXML
    private Label totalFemaleCapacity;
    @FXML
    private Label totalMaleCapacity;
    @FXML
    private Label totalRoomsCountLabel;


    private DormService dormService = new DormService();
    private UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set localized texts
        lblGenderDistribution.setText(resourceBundle.getString("lblGenderDistribution"));
        lblMaleUsers.setText(resourceBundle.getString("lblMaleUsers"));
        lblFemaleUsers.setText(resourceBundle.getString("lblFemaleUsers"));
        lblTotalUsers.setText(resourceBundle.getString("lblTotalUsers"));
        lblTotalRooms.setText(resourceBundle.getString("lblTotalRooms"));

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
        long totalMaleCapacityRoom = dormService.getTotalCapacityForMaleRooms();
        long totalFemaleCapacityRoom = dormService.getTotalCapacityForFemaleRooms();

//        System.out.println(totalMaleCapacity + " " + totalFemaleCapacity);
        long totalCapacity = dormService.getTotalCapacity();

        totalRoomsCountLabel.setText(String.valueOf(totalCapacity));
        totalFemaleCapacity.setText(String.valueOf(totalFemaleCapacityRoom));
        totalMaleCapacity.setText(String.valueOf(totalMaleCapacityRoom));


        updateDormPieChart(totalMaleCapacityRoom, totalFemaleCapacityRoom);
    }

    private void updateGenderPieChart(long maleCount, long femaleCount) {
        PieChart.Data maleData = new PieChart.Data("Male", maleCount);
        PieChart.Data femaleData = new PieChart.Data("Female", femaleCount);
        genderPieChart.getData().clear();
        genderPieChart.getData().addAll(maleData, femaleData);
    }

    private void updateDormPieChart(long maleCapacity, long femaleCapacity) {
        PieChart.Data maleDormData = new PieChart.Data("Male Dorms", maleCapacity);
        PieChart.Data femaleDormData = new PieChart.Data("Female Dorms", femaleCapacity);
        dormPieChart.getData().clear();
        dormPieChart.getData().addAll(maleDormData, femaleDormData);
    }
}