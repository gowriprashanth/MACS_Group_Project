package com.project.accomatch.Repository;

import com.project.accomatch.Credentials;
import com.project.accomatch.Model.LeaseHolderModel;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class LeaseHolderGenderTableOperations {
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
    public boolean createGenderReferences(LeaseHolderModel leaseHolderModel,int leaseholder_application_id){
        try {
            Connection connect;
            Statement statement;
            // Connect to the database.
            getCredentials();
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.execute("use CSCI5308_4_DEVINT;");
            String sql = "INSERT INTO leaseholder_gender_preferences (application_id,gender_pref)"+
                    "VALUES (?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            for(String gender_preference :leaseHolderModel.getGender_preferences()){
                stmt.setInt(1,leaseholder_application_id);
                stmt.setString(2,gender_preference);
                stmt.addBatch();
            }
            stmt.executeBatch();
            stmt.close();
            connect.close();
            return true;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
