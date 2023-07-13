package com.project.accomatch.Controller;
import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Service.Implementation.LeaseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/leaseowner/applicant")
public class LeaseApplicationController {
    @Autowired
    private LeaseApplicationService applicantService;

    @GetMapping("/get/list/applicant/{application_id}")
    public List<Applicant> getListOfPosts(@PathVariable int application_id) {
        return applicantService.getListOfApplicants(application_id);
    }

}
