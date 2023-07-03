package com.project.accomatch.Repository;

import com.project.accomatch.Model.HouseSeekerModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class HouseSeekerTableOperations {
    @Value("${username.db.accomatch}")
    private String username;

    @Value("${password.db.accomatch}")
    private String password;

    @Value("${Connection.db.accomatch}")
    private String JDBC;
    public int createAD(HouseSeekerModel houseSeekerModel) {
        try {
            Connection connect;
            Statement statement;
            // Connect to the database.
            //getCredentials();
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //   statement.execute("use accomatch;");
            String sql = "INSERT INTO houseseeker_ads (user_id,location_city,room_type,other_preferences,start_date,createdAt,updatedAt)"+
                    "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,houseSeekerModel.getUser_id());
            stmt.setString(2,houseSeekerModel.getLocation_city());
            stmt.setString(3,houseSeekerModel.getRoom_type());
            stmt.setString(4,houseSeekerModel.getOther_preferences());
            stmt.setDate(5, new java.sql.Date(houseSeekerModel.getStart_date().getTime()));
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(6,currentTimestamp);
            stmt.setTimestamp(7,currentTimestamp);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int key = 0;
            if (rs.next()) {
                key = rs. getInt(1);
                // Use the generated key as needed
            }
            stmt.close();
            connect.close();
            return key;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
