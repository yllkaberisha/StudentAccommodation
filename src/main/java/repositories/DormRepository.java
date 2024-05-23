package repositories;

import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DormRepository {

    public long countMaleDorms() {
        String query = "SELECT COUNT(*) FROM Dorms WHERE type = 'M'";
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return result.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long countFemaleDorms() {
        String query = "SELECT COUNT(*) FROM Dorms WHERE type = 'F'";
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return result.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
