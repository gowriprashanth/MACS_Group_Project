package com.project.accomatch.Controller;

import com.project.accomatch.Model.Applicant;
import com.project.accomatch.Model.HouseSeekerModel;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.HouseSeekerService;
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
    @GetMapping("/getListOfAllApplicantPosts")
    public List<HouseSeekerModel> getListOfAllApplicantPosts(){
        return houseSeekerService.getListOfAllApplicantPosts();
    }
    @PostMapping("/create")
    public String createAD(@RequestBody Map<String, Object> requestBody){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String user_idStr = (String) requestBody.get("user_id");
            int user_id = Integer.parseInt(user_idStr);
            String location_city = (String) requestBody.get("location_city");
            String room_type = (String) requestBody.get("room_type");
            String other_preferences = (String) requestBody.get("other_preferences");
            String start_dateStr = (String) requestBody.get("start_date");
            Date start_date = sdf.parse(start_dateStr);
            ArrayList<String> food_preferences = (ArrayList<String>) requestBody.get("food_preferences");
            ArrayList<String> gender_preferences = (ArrayList<String>) requestBody.get("gender_preferences");
            HouseSeekerModel houseSeekerModel = new HouseSeekerModel();
            houseSeekerModel.setUser_id(user_id);
            houseSeekerModel.setLocation_city(location_city);
            houseSeekerModel.setRoom_type(room_type);
            houseSeekerModel.setStart_date(start_date);
            houseSeekerModel.setOther_preferences(other_preferences);
            houseSeekerModel.setFood_preferences(food_preferences);
            houseSeekerModel.setGender_preferences(gender_preferences);
            return houseSeekerService.createAD(houseSeekerModel);
        } catch (Exception e){
            return e.getMessage();
        }
    }


}
