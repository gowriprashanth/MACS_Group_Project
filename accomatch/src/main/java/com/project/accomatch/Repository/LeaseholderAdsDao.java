package com.project.accomatch.Repository;

import com.project.accomatch.Model.Posts;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaseholderAdsDao {

    @Value("${username.db.accomatch}")
    private String username;

    @Value("${password.db.accomatch}")
    private String password;

    @Value("${Connection.db.accomatch}")
    private String JDBC;

    public List<Posts> getListOfPosts() {
        List<Posts> listOfPosts = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM leaseholder_ads")) {

            while (resultSet.next()) {
                int leaseholderApplicationId = resultSet.getInt("leaseholder_application_id");
                int userId = resultSet.getInt("user_id");
                String title = resultSet.getString("title");
                String subtitle = resultSet.getString("subtitle");
                String address = resultSet.getString("address");
                String locationCity = resultSet.getString("location_city");
                int size = resultSet.getInt("size");
                String roomType = resultSet.getString("room_type");
                String document = resultSet.getString("document_link");
                double rent = resultSet.getDouble("rent");
                String otherPreferences = resultSet.getString("other_preferences");
                Date startDate = resultSet.getDate("start_date");
                int startAge = resultSet.getInt("start_age");
                int endAge = resultSet.getInt("end_age");
                boolean isVerified = resultSet.getBoolean("is_verified");
                Date createdAt = resultSet.getTimestamp("createdAt");
                Date updatedAt = resultSet.getTimestamp("updatedAt");

                Posts post = new Posts(leaseholderApplicationId, userId, title, subtitle, address, locationCity, size,
                        roomType, document, rent, otherPreferences, startDate, startAge, endAge, isVerified,
                        createdAt, updatedAt);

                listOfPosts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        return listOfPosts;
    }
}
