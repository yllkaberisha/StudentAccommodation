package repositories;

import models.AdminUser;
import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRoomRepository {

    private static Connection connection = DBConnector.getConnection();

    public List<AdminUser> getAllRooms() {
        List<AdminUser> rooms = new ArrayList<>();
        String query = "SELECT roomID, roomType, capacity, floor FROM ROOM";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                AdminUser room = new AdminUser(
                        result.getString("roomID"),
                        result.getString("roomType"),
                        result.getString("capacity"),
                        result.getString("floor")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
        return rooms;
    }


    public void addRoom(String roomID, String roomType, String capacity, String floor) {
        String query = "INSERT INTO ROOM (roomID, roomType, capacity, floor) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, roomID);
            pst.setString(2, roomType);
            pst.setString(3, capacity);
            pst.setString(4, floor);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
    }

    public void updateRoom(String roomID, String roomType, String capacity, String floor) {
        String query = "UPDATE ROOM SET roomType = ?, capacity = ?, floor = ? WHERE roomID = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, roomType);
            pst.setString(2, capacity);
            pst.setString(3, floor);
            pst.setString(4, roomID);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
    }

    public void deleteRoom(String roomID) {
        String query = "DELETE FROM ROOM WHERE roomID = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, roomID);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
    }
    public int getTotalRooms() {
        int totalRooms = 0;
        String query = "SELECT COUNT(*) FROM ROOM";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                totalRooms = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
        return totalRooms;
    }

    public int getTotalRoomsByType(String roomType) {
        int totalRooms = 0;
        String query = "SELECT COUNT(*) FROM ROOM WHERE roomType = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, roomType);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                totalRooms = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
        return totalRooms;
    }

    public List<AdminUser> getRoomsByFloor(int floor) {
        List<AdminUser> rooms = new ArrayList<>();
        String query = "SELECT roomID, roomType, capacity, floor FROM ROOM WHERE floor = ?";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, floor);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                AdminUser room = new AdminUser(
                        result.getString("roomID"),
                        result.getString("roomType"),
                        result.getString("capacity"),
                        result.getString("floor")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }
        return rooms;
    }

    // Implement methods to perform other database operations related to rooms
}
