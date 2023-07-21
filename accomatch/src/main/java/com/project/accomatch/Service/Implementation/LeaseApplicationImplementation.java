package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Repository.LeaseApplicationRepository;
import com.project.accomatch.Service.LeaseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeaseApplicationImplementation implements LeaseApplicationService {

    @Autowired
    LeaseApplicationRepository leaseApplcationRepository;
    public List<Applicant> getListOfApplicants(int application_id) {
        return leaseApplcationRepository.getListOfApplicant(application_id);
    }

}
