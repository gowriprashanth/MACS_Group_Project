package com.project.accomatch.Model.HouseSeeker;

import java.util.ArrayList;
import java.util.Date;

public interface HouseSeekerModelBuilderInterface {
    HouseSeekerModelBuilderInterface setUserId(int userId);
    HouseSeekerModelBuilderInterface setLocationCity(String locationCity);
    HouseSeekerModelBuilderInterface setRoomType(String roomType);
    HouseSeekerModelBuilderInterface setOtherPreferences(String otherPreferences);
    HouseSeekerModelBuilderInterface setStartDate(Date startDate);
    HouseSeekerModelBuilderInterface setFoodPreferences(ArrayList<String> foodPreferences);
    HouseSeekerModelBuilderInterface setGenderPreferences(ArrayList<String> genderPreferences);
    HouseSeekerModelBuilderInterface setName(String name);

    HouseSeekerModel build();
}
