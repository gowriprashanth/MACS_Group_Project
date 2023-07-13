package com.project.accomatch.Controller;

import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Model.HouseSeekerModel;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.HouseSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/houseSeeker")
public class HouseSeekerController {
    @Autowired
    private HouseSeekerService houseSeekerService;
    @GetMapping("/getListOfAllApplicantPosts")
    public List<HouseSeekerModel> getListOfAllApplicantPosts(){
        return houseSeekerService.getListOfAllApplicantPosts();
    }
    @PostMapping("/create")
    public String createAD(@RequestBody HouseSeekerModel houseSeekerModel){
        try {
            return houseSeekerService.createAD(houseSeekerModel);
        } catch (Exception e){
            return e.getMessage();
        }
    }


}
