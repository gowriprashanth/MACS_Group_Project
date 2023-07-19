package com.project.accomatch.Repository;

import com.project.accomatch.Model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, String> LoginUser(UserModel model){

        try{
            Connection connect;
            Statement statement;
            ResultSet rs;
            // Connect to the database.
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            rs = statement.executeQuery("select * from user where email = '"+model.getEmail() +"' and `password` = '"+model.getPassword() +"';");
            if(rs.next()){
                Map<String, String > returnMap = new HashMap<>();
                returnMap.put("Email", rs.getString("email"));
                returnMap.put("User_id", rs.getString("user_id"));
                returnMap.put("Name", rs.getString("name"));
                int admin = rs.getInt("is_admin");
                int leaseHolder = rs.getInt("is_leaseholder");
                if(admin == 0 && leaseHolder == 0){
                    returnMap.put("type", "AP");
                }
                else if(admin == 1 && leaseHolder == 0){
                    returnMap.put("type", "AD");
                }
                else if(admin == 0 && leaseHolder == 1){
                    returnMap.put("type", "LH");
                }
                else{
                    returnMap.put("type", "AP");
                }

                returnMap.put("Status", "Success");
                rs.close();
                statement.close();
                connect.close();
                return returnMap;
            }
            else{
                rs.close();
                statement.close();
                connect.close();
                Map<String, String > returnMap = new HashMap<>();
                returnMap.put("Status", "Fail");
                return returnMap;
            }

        }catch(Exception e){
            Map<String, String > returnMap = new HashMap<>();
            returnMap.put("Status", e.getMessage());
            return returnMap;
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

    public UserModel getUserInfo(int id){

        try{
            Connection connect;
            Statement statement;
            ResultSet rs;
            // Connect to the database.
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            //statement.execute("use accomatch;");
            rs = statement.executeQuery("SELECT user_id, email, `name`, password, age, gender, mobile, address, is_admin, is_leaseholder, createdAt, updatedAt FROM user " +
                    "where user_id = '"+id+"';");
            UserModel userModel = new UserModel();
            if(rs.next()){
                userModel.setUserID(rs.getInt(1));
                userModel.setEmail(rs.getString(2));
                userModel.setName(rs.getString(3));
                userModel.setPassword(rs.getString(4));
                userModel.setAge(rs.getInt(5));
                userModel.setGender(rs.getString(6));
                userModel.setMobile(rs.getString(7));
                userModel.setAddress(rs.getString(8));
                userModel.setIs_admin(rs.getInt(9));
                userModel.setIs_leaseholder(rs.getInt(10));
            }
            else{
                statement.close();
                connect.close();
                return new UserModel();
            }
            statement.close();
            connect.close();
            return userModel;
        }catch(Exception e){
            return new UserModel();
        }
    }

}
