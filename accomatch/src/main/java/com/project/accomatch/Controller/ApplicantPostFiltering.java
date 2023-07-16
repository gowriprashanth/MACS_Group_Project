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
        String[] gender_pref = jsonMap.get("gender_pref").split(",");
        String[] food_pref = jsonMap.get("food_pref").split(",");
        String age = jsonMap.get("age");
        String room_type = jsonMap.get("room_type");
        return applicantPostFilterService.filterPost(gender_pref, food_pref, age, room_type);
    }
}
