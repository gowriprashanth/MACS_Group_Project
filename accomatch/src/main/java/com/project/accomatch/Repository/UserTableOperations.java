package com.project.accomatch.Repository;

import com.project.accomatch.Model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class UserTableOperations {

    @Value("${username.db.accomatch}")
    private String username;

    @Value("${password.db.accomatch}")
    private String password;

    @Value("${Connection.db.accomatch}")
    private String JDBC;
    public String signUpUser(UserModel model){

        try{
            Connection connect;
            Statement statement;
            // Connect to the database.
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //statement.execute("use accomatch;");

            String sql = "INSERT INTO user (email, `name`, password, age, gender, mobile, address, is_admin, is_leaseholder, createdAt, updatedAt) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connect.prepareStatement(sql);

            stmt.setString(1, model.getEmail());
            stmt.setString(2, model.getName());
            stmt.setString(3, model.getPassword());
            stmt.setInt(4, model.getAge());
            stmt.setString(5, model.getGender());
            stmt.setString(6, model.getMobile());
            stmt.setString(7, model.getAddress());
            stmt.setInt(8, model.getIs_admin());
            stmt.setInt(9, model.getIs_leaseholder());
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(10, currentTimestamp);
            Timestamp currentTimestamp2 = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(11, currentTimestamp2);

            stmt.executeUpdate();

            stmt.close();
            statement.close();
            connect.close();
            return "Success";

        }catch(Exception e){
            return "Error";
        }



    }

    public String LoginUser(UserModel model){

        try{
            Connection connect;
            Statement statement;
            ResultSet rs;
            // Connect to the database.
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //statement.execute("use accomatch;");

            rs = statement.executeQuery("select * from user where email = '"+model.getEmail() +"' and `password` = '"+model.getPassword() +"';");
            if(rs.next()){
                rs.close();
                statement.close();
                connect.close();
                return "Success";
            }
            else{
                rs.close();
                statement.close();
                connect.close();
                return "Fail";
            }

        }catch(Exception e){
            return "Error";
        }

    }

    public String CheckMailID(String Mail){

        try{
            Connection connect;
            Statement statement;
            ResultSet rs;
            // Connect to the database.
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //statement.execute("use accomatch;");

            rs = statement.executeQuery("select * from user where email = '"+Mail +"';");
            if(rs.next()){
                statement.close();
                connect.close();
                return "Success";
            }
            else{
                statement.close();
                connect.close();
                return "Fail";
            }

        }catch(Exception e){
            return "Error";
        }

    }

    public String ForgotPassword(UserModel model){

        try{
            Connection connect;
            Statement statement;
            ResultSet rs;
            // Connect to the database.
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //statement.execute("use accomatch;");
            String sql = "UPDATE user SET password = ? WHERE email = ?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setString(1, model.getPassword());
            stmt.setString(2, model.getEmail());
            stmt.executeUpdate();
            statement.close();
            connect.close();
            return "Success";
        }catch(Exception e){
            return "Error";
        }

    }
}
