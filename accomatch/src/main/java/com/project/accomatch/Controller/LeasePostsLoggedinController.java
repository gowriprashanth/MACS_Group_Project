package com.project.accomatch.Controller;

import com.project.accomatch.Exception.InvalidInputException;
import com.project.accomatch.LoggerPack.LoggerClass;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.LeasePostsLoggedinService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/api/leaseholder/loggedinapplicant")
public class LeasePostsLoggedinController {
    @Autowired
    public LeasePostsLoggedinService applicantService;

    Logger logger = LoggerClass.getLogger();
    @GetMapping("/get/list/applicant/{user_id}")
    public List<Posts> getListOfLoggedinPosts(@PathVariable int user_id) {
        if (user_id <= 0) {
            throw new InvalidInputException("Invalid application ID provided.");
        }
        logger.info("Lease Post loogedin controller active");
        return applicantService.getListOfLoggedinApplicants(user_id);
    }

}
