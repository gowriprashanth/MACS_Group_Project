package com.project.accomatch.Controller;

import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Model.HouseSeekerModel;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.HouseSeekerService;
import com.project.accomatch.Service.Implementation.CreateApplicationFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/houseSeeker")
public class HouseSeekerController {
    @Autowired
    private HouseSeekerService houseSeekerService;
    @Autowired
    private CreateApplicationFactory createApplicationService;

    @GetMapping("/getListOfAllApplicantPosts")
    public List<HouseSeekerModel> getListOfAllApplicantPosts(){
        return houseSeekerService.getListOfAllApplicantPosts();
    }
    @PostMapping("/create")
    public String createAD(@RequestBody Map<String, Object> requestBody){
        try {
            return createApplicationService.createAD(requestBody);
        } catch (Exception e){
            return e.getMessage();
        }
    }


}
