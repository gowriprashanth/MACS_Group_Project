package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Model.HouseSeeker.HouseSeekerModel;
import com.project.accomatch.Repository.*;
import com.project.accomatch.Service.HouseSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseSeekerServiceImplementation implements HouseSeekerService {
    @Autowired
    HouseSeekerTableOperations houseSeekerTableOperations;
    @Autowired
    HouseSeekerFoodTableOperations houseSeekerFoodTableOperations;
    @Autowired
    HouseSeekerGenderTableOperations houseSeekerGenderTableOperations;
    @Override
    public String createAD(HouseSeekerModel houseSeekerModel) {
        try {
            int houseseeker_application_id = houseSeekerTableOperations.createAD(houseSeekerModel);
            boolean isFoodPreferencesAdded = houseSeekerFoodTableOperations.createFoodReferences(houseSeekerModel,houseseeker_application_id);
            boolean isGenderPreferencesAdded = houseSeekerGenderTableOperations.createGenderReferences(houseSeekerModel,houseseeker_application_id);

            return "Ad created successfully";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HouseSeekerModel> getListOfAllApplicantPosts() {
        try {

            return houseSeekerTableOperations.getListOfAllApplicantPosts();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

