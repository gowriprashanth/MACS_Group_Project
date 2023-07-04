package com.project.accomatch.Controller;

import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.ApplicantPostFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/applicant")
public class ApplicantPostFiltering {

    @Autowired
    private ApplicantPostFilterService applicantPostFilterService;

    @PostMapping("/posts/filter")
    public List<Posts> signUp(@RequestBody Map<String, String> jsonMap){
        return applicantPostFilterService.filterPost(jsonMap);
    }
}
