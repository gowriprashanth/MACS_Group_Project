package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Model.LeaseHolderModel;
import com.project.accomatch.Repository.Implementation.LeaseHolderFoodTableOperations;
import com.project.accomatch.Repository.Implementation.LeaseHolderGenderTableOperations;
import com.project.accomatch.Repository.Implementation.LeaseHolderImagesTableOperations;
import com.project.accomatch.Repository.Implementation.LeaseHolderTableOperations;
import com.project.accomatch.Service.LeaseHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaseHolderServiceImplementation implements LeaseHolderService {
    @Autowired
    LeaseHolderTableOperations leaseHolderTableOperations;
    @Autowired
    LeaseHolderFoodTableOperations leaseHolderFoodTableOperations;
    @Autowired
    LeaseHolderGenderTableOperations leaseHolderGenderTableOperations;
    @Autowired
    LeaseHolderImagesTableOperations leaseHolderImagesTableOperations;
    @Override
    public String createAD(LeaseHolderModel leaseHolderModel) {
        try {
            int leaseholder_application_id = leaseHolderTableOperations.createAD(leaseHolderModel);
            boolean isFoodPreferencesAdded = leaseHolderFoodTableOperations.createFoodReferences(leaseHolderModel,leaseholder_application_id);
            boolean isImagesAdded = leaseHolderImagesTableOperations.addImages(leaseHolderModel,leaseholder_application_id);
            boolean isGenderPreferencesAdded = leaseHolderGenderTableOperations.createGenderReferences(leaseHolderModel,leaseholder_application_id);

            return "Ad created successfully";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
