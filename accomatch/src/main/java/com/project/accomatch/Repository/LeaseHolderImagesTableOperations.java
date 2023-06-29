package com.project.accomatch.Repository;

import com.project.accomatch.Credentials;
import com.project.accomatch.Model.LeaseHolderModel;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class LeaseHolderImagesTableOperations {
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
    public boolean addImages(LeaseHolderModel leaseHolderModel,int leaseholder_application_id){
        try {
            Connection connect;
            Statement statement;
            // Connect to the database.
            getCredentials();
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            statement.execute("use accomatch;");
            String sql = "INSERT INTO leaseholder_images (application_id,image_link)"+
                    "VALUES (?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            for(String image_link :leaseHolderModel.getImages()){
                stmt.setInt(1,leaseholder_application_id);
                stmt.setString(2,image_link);
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

