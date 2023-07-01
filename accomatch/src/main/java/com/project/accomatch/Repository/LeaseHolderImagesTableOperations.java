package com.project.accomatch.Repository;

//import com.project.accomatch.Credentials;
import com.project.accomatch.Model.LeaseHolderModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class LeaseHolderImagesTableOperations {
    @Value("${username.db.accomatch}")
    private String username;

    @Value("${password.db.accomatch}")
    private String password;

    @Value("${Connection.db.accomatch}")
    private String JDBC;
    public boolean addImages(LeaseHolderModel leaseHolderModel,int leaseholder_application_id){
        try {
            Connection connect;
            Statement statement;
            // Connect to the database.
            //getCredentials();
            connect = DriverManager.getConnection(JDBC, username, password);
            // Create a statement object.
            statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

         //   statement.execute("use accomatch;");
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

