package repositories;

import services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DormRepository {

        public long getTotalCapacityForRoomType(String roomType) {
            String query = "SELECT SUM(capacity) FROM ROOM WHERE roomType = ?";
            Connection connection = DBConnector.getConnection();
            try{
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1, roomType);
                ResultSet result = pst.executeQuery();
                if(result.next()){
                    return result.getLong(1);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return 0;
        }

}
