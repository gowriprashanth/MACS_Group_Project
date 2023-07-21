package com.project.accomatch.Model.HouseSeeker;

import java.util.ArrayList;
import java.util.Date;

public class HouseSeekerModel {
    private int user_id;
    private int  housekeeper_application_Id;
    private String location_city;
    private String room_type;
    private String other_preferences;
    private String name;
    private Date start_date, created_At, updated_At;

    private HouseSeekerModel(){}


    private ArrayList<String> food_preferences,gender_preferences;



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public String getLocation_city() {
        return location_city;
    }

    public void setLocation_city(String location_city) {
        this.location_city = location_city;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getOther_preferences() {
        return other_preferences;
    }

    public void setOther_preferences(String other_preferences) {
        this.other_preferences = other_preferences;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public ArrayList<String> getFood_preferences() {
        return food_preferences;
    }

    public void setFood_preferences(ArrayList<String> food_preferences) {
        this.food_preferences = food_preferences;
    }

    public ArrayList<String> getGender_preferences() {
        return gender_preferences;
    }

    public void setGender_preferences(ArrayList<String> gender_preferences) {
        this.gender_preferences = gender_preferences;
    }

    public void setName(String name) {
        this.name = name;
    }

}
