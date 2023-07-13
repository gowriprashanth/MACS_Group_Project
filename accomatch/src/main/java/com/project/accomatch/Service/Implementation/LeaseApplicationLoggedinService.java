package com.project.accomatch.Service.Implementation;
import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Repository.LeaseApplicationLoggedinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LeaseApplicationLoggedinService {

    @Autowired
    LeaseApplicationLoggedinRepository leaseApplicationLoggedinRepository;
    public List<Applicant> getListOfLoggedinApplicants(int application_id) {
        return leaseApplicationLoggedinRepository.getListOfLoggedinApplicant(application_id);
    }
}
