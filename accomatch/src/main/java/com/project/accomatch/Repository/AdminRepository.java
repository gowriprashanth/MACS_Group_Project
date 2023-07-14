package com.project.accomatch.Repository;

import com.project.accomatch.Model.Posts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class AdminRepository {

    @Value("${username.db.accomatch}")
    private String username;

    @Value("${password.db.accomatch}")
    private String password;

    @Value("${Connection.db.accomatch}")
    private String JDBC;
    public String OneAd(Posts posts){
        try (Connection connect = DriverManager.getConnection(JDBC, username, password);
             Statement statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            String sql = "update leaseholder_ads set is_verified = ? where leaseholder_application_id = ?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, posts.isVerified());
            stmt.setInt(2, posts.getLeaseholderApplicationId());
            stmt.executeUpdate();
            stmt.close();
            return "Success";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error";
        }
        }

    public String AllAd(Posts posts){
        try (Connection connect = DriverManager.getConnection(JDBC, username, password);
             Statement statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            String sql = "update leaseholder_ads set is_verified = ?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, posts.isVerified());
            stmt.executeUpdate();
            stmt.close();
            return "Success";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error";
        }
    }
    }

