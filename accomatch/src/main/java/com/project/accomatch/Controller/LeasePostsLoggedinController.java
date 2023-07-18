package com.project.accomatch.Controller;
import com.project.accomatch.Exception.InvalidInputException;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.Implementation.LeasePostLoggedinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/leaseowner/loggedinapplicant")
public class LeasePostsLoggedinController {
    @Autowired
    private LeasePostLoggedinService applicantService;
    @GetMapping("/get/list/applicant/{user_id}")
    public List<Posts> getListOfLoggedinPosts(@PathVariable int user_id) {
        if (user_id <= 0) {
            throw new InvalidInputException("Invalid application ID provided.");
        }
        return applicantService.getListOfLoggedinApplicants(user_id);
    }

}
