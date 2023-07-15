package com.project.accomatch.Repository;

import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Model.Review;
import com.project.accomatch.Model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewRepository {

    @Value("${username.db.accomatch}")
    private String username;

    @Value("${password.db.accomatch}")
    private String password;

    @Value("${Connection.db.accomatch}")
    private String JDBC;
    public List<Review> getAllReviews(int application_id) {
        List<Review> listOfReviews = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC, username, password);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT u.name, u.user_id, rt.application_id, rt.rating_id, rt.rating, rt.review, rt.rating FROM user u JOIN rating_table rt ON u.user_id = rt.user_id where rt.application_id = ?"

             )) {
            statement.setInt(1,application_id);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next()) {
                //int applicationId = resultSet.getInt("application_id");
                int userId = resultSet.getInt("user_id");
                int applicationId = resultSet.getInt("application_id");
                int ratingId = resultSet.getInt("rating_id");
                int rating = resultSet.getInt("rating");
                String comment = resultSet.getString("review");
                String name = resultSet.getString("name");

                Review reviews = new Review(ratingId, userId, applicationId, name, rating, comment);

                listOfReviews.add(reviews);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        return listOfReviews;
    }

}
