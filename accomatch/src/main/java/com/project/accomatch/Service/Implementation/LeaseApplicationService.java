package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Repository.LeaseApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeaseApplicationService {

    @Autowired
    LeaseApplicationRepository leaseApplcationRepository;
    public List<Applicant> getListOfApplicants(int application_id) {
        return leaseApplcationRepository.getListOfApplicant(application_id);
    }
}
