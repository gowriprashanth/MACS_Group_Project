package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Exception.DataAccessException;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Repository.LeaseHolderFoodTableOperations;
import com.project.accomatch.Repository.LeaseHolderGenderTableOperations;
import com.project.accomatch.Repository.LeaseHolderImagesTableOperations;
import com.project.accomatch.Repository.LeaseholderAdsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LeaseHolderDashboardService {

    @Autowired
    LeaseholderAdsDao leaseholderAdsDao;

    @Autowired
    LeaseHolderFoodTableOperations leaseHolderFoodTableOperations;
    @Autowired
    LeaseHolderGenderTableOperations leaseHolderGenderTableOperations;
    @Autowired
    LeaseHolderImagesTableOperations leaseHolderImagesTableOperations;
    public List<Posts> getListOfPosts() {
        try {
            return leaseholderAdsDao.getListOfPosts();
        } catch (SQLException e) {
            throw new DataAccessException("Failed to retrieve the list of posts.", e);
        }
    }


    public List<Posts> getListOfPostsByStatus(int status) {
        return leaseholderAdsDao.getListOfPostsByStatus( status);
    }


    public List<String> getListOfgenderPreferencesByApplicationId(int applicantionId){
      return   leaseHolderGenderTableOperations.getGenderPreferencesByApplicationId(applicantionId);
    }

    public List<String> getListOfFoodPreferencesByApplicationId(int applicantionId){
        return   leaseHolderFoodTableOperations.getFoodPreferencesByApplicationId(applicantionId);
    }

    public List<String> getListOfImagesByApplicationId(int applicantionId){
        return   leaseHolderImagesTableOperations.getImagesByApplicationId(applicantionId);
    }

    public Posts getPostByApplicationId(int applicationId){
        return leaseholderAdsDao.getPostByApplicationId(applicationId);
    }

    public List<Posts> getListOfPersonalPosts(int userID){
        return leaseholderAdsDao.getListOfPersonalPosts(userID);
    }
}
