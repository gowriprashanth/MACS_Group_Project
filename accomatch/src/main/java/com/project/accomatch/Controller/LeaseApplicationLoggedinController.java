package com.project.accomatch.Controller;
import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Service.Implementation.LeaseApplicationLoggedinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/leaseowner/loggedinapplicant")
public class LeaseApplicationLoggedinController {
    @Autowired
    private LeaseApplicationLoggedinService applicantService;
    @GetMapping("/get/list/applicant/{application_id}")
    public List<Applicant> getListOfLoggedinPosts(@PathVariable int application_id) {
        return applicantService.getListOfLoggedinApplicants(application_id);
    }

}
