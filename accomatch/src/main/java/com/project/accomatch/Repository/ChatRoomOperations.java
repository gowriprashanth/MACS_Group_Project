package com.project.accomatch.Repository;

import com.project.accomatch.Model.ChatRoomModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ChatRoomOperations {
    @Value("${username.db.accomatch}")
    private String username;

    @Value("${password.db.accomatch}")
    private String password;

    @Value("${Connection.db.accomatch}")
    private String JDBC;
    public int createChatRoom(ChatRoomModel chatRoomModel){
        try{
            Connection connect;
            Statement statement;
            // Connect to the database.
            //getCredentials();
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //   statement.execute("use accomatch;");
            String sql = "INSERT INTO room (user_1_id,user_2_id)"+
                    "VALUES (?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,chatRoomModel.getUser_1_id());
            stmt.setInt(2,chatRoomModel.getUser_2_id());
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
