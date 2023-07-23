package com.project.accomatch.Controller;
import com.project.accomatch.Exception.InvalidInputException;
import com.project.accomatch.LoggerPack.LoggerClass;
import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Service.Implementation.LeaseApplicationImplementation;
import com.project.accomatch.Service.LeaseApplicationService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/leaseowner/applicant")
public class LeaseApplicationController {
    @Autowired
    public LeaseApplicationService applicantService;

    Logger logger = LoggerClass.getLogger();
    @GetMapping("/get/list/applicant/{application_id}")
    public List<Applicant> getListOfPosts(@PathVariable int application_id) {
        if (application_id <= 0) {
            throw new InvalidInputException("Invalid application ID provided.");
        }
        logger.info("Lease Applicant controller active");
        return applicantService.getListOfApplicants(application_id);
    }

}
