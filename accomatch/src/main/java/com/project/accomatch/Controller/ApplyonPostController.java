package com.project.accomatch.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.accomatch.Model.ChatRoomModel;
import com.project.accomatch.Model.LeaseHolderApplicantModel;
import com.project.accomatch.Service.ApplyonPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/applicant")
public class ApplyonPostController {
    @Autowired
    private ApplyonPostService applyonPostService;
    @PostMapping("/apply")
    public String apply(@RequestBody LeaseHolderApplicantModel leaseHolderApplicantModel){
        try{
            ChatRoomModel chatRoomModel = new ChatRoomModel();
            chatRoomModel.setUser_2_id(leaseHolderApplicantModel.getUser_id());
            return applyonPostService.apply(leaseHolderApplicantModel,chatRoomModel);
        } catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("/isApplied")
    public boolean isUserApplied(@RequestBody Map<String,Object> requestBody){
        try{
            String user_idStr = (String) requestBody.get("user_id");
            int user_id = Integer.parseInt(user_idStr);
            String application_idStr = (String) requestBody.get("application_id");
            int application_id = Integer.parseInt(application_idStr);
            LeaseHolderApplicantModel leaseHolderApplicantModel = new LeaseHolderApplicantModel();
            leaseHolderApplicantModel.setUser_id(user_id);
            leaseHolderApplicantModel.setApplication_id(application_id);
            return applyonPostService.isApplied(leaseHolderApplicantModel);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
