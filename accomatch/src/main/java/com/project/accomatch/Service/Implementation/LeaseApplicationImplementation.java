package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Exception.ResourceNotFoundException;
import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Repository.Implementation.LeaseApplicationRepository;
import com.project.accomatch.Service.LeaseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class LeaseApplicationImplementation implements LeaseApplicationService {

    @Autowired
    LeaseApplicationRepository leaseApplcationRepository;
    public List<Applicant> getListOfApplicants(int application_id) {
        try {
            return leaseApplcationRepository.getListOfApplicant(application_id);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Failed to retrieve the list of Applicants.");
        }
    }

}
