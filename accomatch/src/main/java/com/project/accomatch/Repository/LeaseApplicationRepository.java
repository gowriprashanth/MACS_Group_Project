package com.project.accomatch.Repository;
import com.project.accomatch.Model.Applicant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.*;

@Repository
public class LeaseApplicationRepository {
    @Value("${username.db.accomatch}")
    private String username;

    @Value("${password.db.accomatch}")
    private String password;

    @Value("${Connection.db.accomatch}")
    private String JDBC;

    public List<Applicant> getListOfApplicant() {
        List<Applicant> listOfApplicants = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("(SELECT user_id,name,email,age,gender,mobile FROM user where user_id =(SELECT user_id FROM leaseholder_applicant ))"
             )) {

            while (resultSet.next()) {
                //int applicationId = resultSet.getInt("application_id");
                int userId = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String mobile = resultSet.getString("mobile");

                Applicant applicant = new Applicant(userId, name, email ,age ,gender ,mobile);

                listOfApplicants.add(applicant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }

        return listOfApplicants;
    }
}
