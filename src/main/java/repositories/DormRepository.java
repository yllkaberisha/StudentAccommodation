package repositories;

import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DormRepository {

    public long getTotalCapacityForRoomType(String roomType) {
        String query = "SELECT SUM(capacity) FROM ROOM WHERE roomType = ?";
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, roomType);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
