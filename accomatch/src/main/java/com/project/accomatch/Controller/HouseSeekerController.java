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

    /**
     *
     * Retrieves a list of all applicant posts.
     * @auther Dhrumil Vimalbhai Gosaliya
     * @return list of HouseSeekerModel objects representing the applicant posts.
     */
    @GetMapping("/getListOfAllApplicantPosts")
    public List<HouseSeekerModel> getListOfAllApplicantPosts(){
        return houseSeekerService.getListOfAllApplicantPosts();
    }
}
