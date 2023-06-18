package com.project.accomatch.Repository;

import com.project.accomatch.Credentials;
import com.project.accomatch.Model.UserModel;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class UserTableOperations {

    private String username;
    private String password;
    private String JDBC;

    private void getCredentials(){
        Credentials credentials = new Credentials();
        credentials.credentialsFinder();
        username = credentials.getUsername();
        password = credentials.getPassword();
        JDBC = credentials.getJDBC();
    }
    public String signUpUser(UserModel model){

        try{
            Connection connect;
            Statement statement;
            // Connect to the database.
            getCredentials();
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.execute("use accomatch;");

            String sql = "INSERT INTO users (email, `name`, password, age, gender, mobile, address, is_admin, is_leaseholder, createdAt, updatedAt) " +
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
            return "user added successfully";

        }catch(Exception e){
            return e.getMessage();
        }



    }

    public String LoginUser(UserModel model){

        try{
            Connection connect;
            Statement statement;
            ResultSet rs;
            // Connect to the database.
            getCredentials();
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.execute("use accomatch;");

            rs = statement.executeQuery("select * from users where email = '"+model.getEmail() +"' and `password` = '"+model.getPassword() +"';");
            if(rs.next()){
                rs.close();
                statement.close();
                connect.close();
                return "Login Successful";
            }
            else{
                rs.close();
                statement.close();
                connect.close();
                return "User not found";
            }

        }catch(Exception e){
            return e.getMessage();
        }

    }

    public String CheckMailID(String Mail){

        try{
            Connection connect;
            Statement statement;
            ResultSet rs;
            getCredentials();
            // Connect to the database.
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.execute("use accomatch;");

            rs = statement.executeQuery("select * from users where email = '"+Mail +"';");
            if(rs.next()){
                statement.close();
                connect.close();
                return "ID exists";
            }
            else{
                statement.close();
                connect.close();
                return "Email not found in the database. Please Sign Up";
            }

        }catch(Exception e){
            return e.getMessage();
        }

    }

    public String ForgotPassword(UserModel model){

        try{
            Connection connect;
            Statement statement;
            ResultSet rs;
            getCredentials();
            // Connect to the database.
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.execute("use accomatch;");
            String sql = "UPDATE users SET password = ? WHERE email = ?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setString(1, model.getPassword());
            stmt.setString(2, model.getEmail());
            stmt.executeUpdate();
            statement.close();
            connect.close();
            return "Password Updated Successfully";
        }catch(Exception e){
            return e.getMessage();
        }

    }
}
