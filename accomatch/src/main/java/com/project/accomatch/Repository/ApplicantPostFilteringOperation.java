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

    public List<Posts> filterPosts(String[] gp, String[] fp, String age, String rt) {
        List<Posts> listOfFilteredPosts = new ArrayList<>();

            try (Connection connect = DriverManager.getConnection(JDBC, username, password);
                    Statement statement = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
                                String defaultValue = "SELECT distinct leaseholder_ads.* " +
                        "FROM leaseholder_ads " +
                        "JOIN leaseholder_gender_preferences ON leaseholder_ads.leaseholder_application_id = leaseholder_gender_preferences.application_id " +
                        "JOIN leaseholder_food_preferences ON leaseholder_ads.leaseholder_application_id = leaseholder_food_preferences.application_id where " +
                                        "(" + "room_type = '"+ rt +"' or room_type IS NULL)"+ " and (" +
                                        age +" >= start_age AND "+age+" <= end_age) and (gender_pref = ";

                StringBuilder stringBuilder = new StringBuilder(defaultValue);

                if (!Objects.equals(gp[0], "")) {
                    for (String element : gp) {
                        stringBuilder.append("'").append(element).append("'").append(" OR ").append(" gender_pref = ");
                    }
                    // Remove the last " OR" from the StringBuilder
                    stringBuilder.delete(stringBuilder.length() - 18, stringBuilder.length());
                    stringBuilder.append(")");
                }
                else{
                    stringBuilder.delete(stringBuilder.length() - 19, stringBuilder.length());
                }
                    stringBuilder.append(" and (food_pref = ");


                if (!Objects.equals(fp[0], "")) {
                    for (String element : fp) {
                        stringBuilder.append("'").append(element).append("'").append(" OR ").append(" food_pref = ");
                    }
                    // Remove the last " OR" from the StringBuilder
                    stringBuilder.delete(stringBuilder.length() - 16, stringBuilder.length());
                    stringBuilder.append(");");
                }
                else{
                    stringBuilder.delete(stringBuilder.length() - 17, stringBuilder.length());
                    stringBuilder.append(";");
                }


                String finalString = stringBuilder.toString();
                System.out.println(finalString);

                PreparedStatement Pstatement = connect.prepareStatement(finalString);
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
//                String defaultValue = "SELECT leaseholder_ads.* " +
//                        "FROM leaseholder_ads " +
//                        "JOIN leaseholder_gender_preferences ON leaseholder_ads.leaseholder_application_id = leaseholder_gender_preferences.application_id " +
//                        "JOIN leaseholder_food_preferences ON leaseholder_ads.leaseholder_application_id = leaseholder_food_preferences.application_id where (" +
//                        "room_type = 'sharing' or room_type IS NULL) and (gender_pref = ";
//                String[] array = {"element1", "element2", "element3"};
//
//                StringBuilder stringBuilder = new StringBuilder(defaultValue);
//
//                if (array.length > 0) {
//                    for (String element : array) {
//                        stringBuilder.append(element).append(" OR ").append(" gender_pref = ");
//                    }
//                    // Remove the last " OR" from the StringBuilder
//                    stringBuilder.delete(stringBuilder.length() - 19, stringBuilder.length());
//                    stringBuilder.append(" or gender_pref is null) ");
//                }
//                if(array.length == 0){
//                    stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
//                    stringBuilder.append("is null)");
//                }
//                    stringBuilder.append(" and (food_pref = ");
//
//
//                if (array.length > 0) {
//                    for (String element : array) {
//                        stringBuilder.append(element).append(" OR ").append(" food_pref = ");
//                    }
//                    // Remove the last " OR" from the StringBuilder
//                    stringBuilder.delete(stringBuilder.length() - 17, stringBuilder.length());
//                    stringBuilder.append(" or gender_pref is null);");
//                }
//                if(array.length == 0){
//                    stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
//                    stringBuilder.append("is null);");
//                }
//
//
//                String finalString = stringBuilder.toString();
//
//                System.out.println(finalString);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return listOfFilteredPosts;
    }
}
