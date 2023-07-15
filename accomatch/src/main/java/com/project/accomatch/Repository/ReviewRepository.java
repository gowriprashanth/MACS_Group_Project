package com.project.accomatch.Repository;

import com.project.accomatch.Model.Review;
import com.project.accomatch.Model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ReviewRepository {

    @Value("${username.db.accomatch}")
    private String username;

    @Value("${password.db.accomatch}")
    private String password;

    @Value("${Connection.db.accomatch}")
    private String JDBC;

    public int createReview(Review review) {
        try {
            Connection connect;
            Statement statement;
            // Connect to the database.
            //getCredentials();
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "INSERT INTO rating_table (user_id,application_id,rating,review)" +
                    "VALUES (?,?,?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, review.getUserId());
            stmt.setInt(2, review.getApplicationId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComment());

            int rs = stmt.executeUpdate();
            stmt.close();
            connect.close();
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}
