package com.project.accomatch.Controller;

import com.project.accomatch.Model.LeaseHolderModel;
import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Service.Implementation.MailSenderClass;
import com.project.accomatch.Service.LeaseHolderService;
import com.project.accomatch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/leaseHolder")
public class LeaseHolderController {
    @Autowired
    private LeaseHolderService leaseHolderService;
    @PostMapping("/create")
    public String createAD(@RequestBody LeaseHolderModel leaseHolderModel){
       try {
           return leaseHolderService.createAD(leaseHolderModel);
       } catch (Exception e){
          return e.getMessage();
       }
    }

}
