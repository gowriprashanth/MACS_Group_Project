package com.project.accomatch.Controller;

import com.project.accomatch.Model.ChatRoomModel;
import com.project.accomatch.Model.LeaseHolderApplicantModel;
import com.project.accomatch.Service.ApplyonPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
