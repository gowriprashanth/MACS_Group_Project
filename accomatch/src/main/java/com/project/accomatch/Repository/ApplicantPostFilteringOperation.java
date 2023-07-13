package com.project.accomatch.Repository;

import com.project.accomatch.Model.Posts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Date;
import java.util.*;

@Repository
public class ApplicantPostFilteringOperation {

    @Value("${username.db.accomatch}")
    private String username;

    @Value("${password.db.accomatch}")
    private String password;

    @Value("${Connection.db.accomatch}")
    private String JDBC;

    public List<Posts> filterPosts(Map<String, String> jsonMap) {
        List<Posts> listOfFilteredPosts = new ArrayList<>();

            try (Connection connect = DriverManager.getConnection(JDBC, username, password);
                    Statement statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
                String query = "SELECT leaseholder_ads.* " +
                        "       FROM leaseholder_ads " +
                        "       JOIN leaseholder_gender_preferences ON leaseholder_ads.leaseholder_application_id = leaseholder_gender_preferences.application_id " +
                        "       JOIN leaseholder_food_preferences ON leaseholder_ads.leaseholder_application_id = leaseholder_food_preferences.application_id " +
                        "       WHERE gender_pref = ? AND food_pref = ?  " +
                        "       AND (? = '' OR ? >= start_age) " +
                        "       AND (? = '' OR ? <= end_age)";

                PreparedStatement Pstatement = connect.prepareStatement(query);
                String preferedGender = null;
                String preferedFood = null;
                if(Objects.equals(jsonMap.get("Male"), "1") && Objects.equals(jsonMap.get("Female"), "0")){
                    preferedGender = "Male";
                }
                else if(Objects.equals(jsonMap.get("Male"), "0") && Objects.equals(jsonMap.get("Female"), "1")){
                    preferedGender = "Female";
                }
                else if(Objects.equals(jsonMap.get("Male"), "0") && Objects.equals(jsonMap.get("Female"), "0")){
                    preferedGender = "none";
                }
                else if(Objects.equals(jsonMap.get("Male"), null) && Objects.equals(jsonMap.get("Female"), null)){
                    preferedGender = "none";
                }

                String ageString = jsonMap.get("age");
                int age = 0;

                if (!ageString.isEmpty()) {
                    try {
                        age = Integer.parseInt(ageString);
                    } catch (NumberFormatException e) {
                        ageString="";
                    }
                }

                if(Objects.equals(jsonMap.get("Veg"), "1") && Objects.equals(jsonMap.get("NonVeg"), "0")){
                    preferedFood = "Veg";
                }
                else if(Objects.equals(jsonMap.get("Veg"), "0") && Objects.equals(jsonMap.get("NonVeg"), "1")){
                    preferedFood = "NonVeg";
                }
                else if(Objects.equals(jsonMap.get("Veg"), "0") && Objects.equals(jsonMap.get("NonVeg"), "0")){
                    preferedFood = "none";
                }
                else if(Objects.equals(jsonMap.get("Veg"), null) && Objects.equals(jsonMap.get("NonVeg"), null)){
                    preferedFood = "none";
                }

                Pstatement.setString(1, preferedGender);
                Pstatement.setString(2, preferedFood);
                if(ageString.isEmpty()){
                    Pstatement.setString(3, ageString);
                    Pstatement.setString(4, ageString);
                    Pstatement.setString(5, ageString);
                    Pstatement.setString(6, ageString);
                }else{
                    Pstatement.setInt(3, age);
                    Pstatement.setInt(4, age);
                    Pstatement.setInt(5, age);
                    Pstatement.setInt(6, age);
                }


                ResultSet resultSet = Pstatement.executeQuery();

                while(resultSet.next()){
                    int leaseholderApplicationId = resultSet.getInt("leaseholder_application_id");
                    int userId = resultSet.getInt("user_id");
                    String title = resultSet.getString("title");
                    String subtitle = resultSet.getString("subtitle");
                    String address = resultSet.getString("address");
                    String locationCity = resultSet.getString("location_city");
                    int size = resultSet.getInt("size");
                    String roomType = resultSet.getString("room_type");
                    String document = resultSet.getString("document_link");
                    double rent = resultSet.getDouble("rent");
                    String otherPreferences = resultSet.getString("other_preferences");
                    java.util.Date startDate = resultSet.getDate("start_date");
                    int startAge = resultSet.getInt("start_age");
                    int endAge = resultSet.getInt("end_age");
                    int isVerified = resultSet.getInt("is_verified");
                    java.util.Date createdAt = resultSet.getTimestamp("createdAt");
                    Date updatedAt = resultSet.getTimestamp("updatedAt");

                    Posts post = new Posts(leaseholderApplicationId, userId, title, subtitle, address, locationCity, size,
                            roomType, document, rent, otherPreferences, startDate, startAge, endAge, isVerified,
                            createdAt, updatedAt);

                    listOfFilteredPosts.add(post);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return listOfFilteredPosts;
    }
}
