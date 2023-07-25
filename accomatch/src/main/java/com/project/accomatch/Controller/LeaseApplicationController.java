package com.project.accomatch.Controller;

import com.project.accomatch.Exception.InvalidInputException;
import com.project.accomatch.LoggerPack.LoggerClass;
import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Service.LeaseApplicationService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/leaseholder/applicant")
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

    @PostMapping("/changeStatus")
    public boolean chanegStatusofApplication(@RequestBody Map<String,Object> requestBody){
        int application_id= (Integer) requestBody.get("application_id");
        int user_id = (Integer) requestBody.get("user_id");
        String status = (String) requestBody.get("status");
        if(application_id<=0 || user_id<=0){
            throw new InvalidInputException("Invalid application Id or user Id provided.");
        }
        logger.info("Lease Applicant Controller Active");
        return applicantService.changeStatusofApplicant(application_id,user_id,status);
    }

}
