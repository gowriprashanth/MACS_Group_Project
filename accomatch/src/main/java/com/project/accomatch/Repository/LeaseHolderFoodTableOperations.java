package com.project.accomatch.Repository;

import com.project.accomatch.Credentials;
import com.project.accomatch.Model.LeaseHolderModel;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class LeaseHolderFoodTableOperations {
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
    public boolean createFoodReferences(LeaseHolderModel leaseHolderModel,int leaseholder_application_id){
        try {
            Connection connect;
            Statement statement;
            // Connect to the database.
            getCredentials();
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.execute("use accomatch;");
            String sql = "INSERT INTO leaseholder_food_preferences (application_id,food_pref)"+
                    "VALUES (?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            for(String food_preference :leaseHolderModel.getFood_preferences()){
                stmt.setInt(1,leaseholder_application_id);
                stmt.setString(2,food_preference);
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
