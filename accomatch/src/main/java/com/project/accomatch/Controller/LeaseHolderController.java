package com.project.accomatch.Controller;

import com.project.accomatch.Model.LeaseHolderModel;
import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Service.Implementation.CreateApplicationFactory;
import com.project.accomatch.Service.Implementation.MailSenderClass;
import com.project.accomatch.Service.LeaseHolderService;
import com.project.accomatch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/application")
public class LeaseHolderController {
    @Autowired
    private LeaseHolderService leaseHolderService;

    @Autowired
    private CreateApplicationFactory createApplicationService;
    @PostMapping("/create")
    public String createAD(@RequestBody Map<String, Object> requestBody){
        try {
            return createApplicationService.createAD(requestBody);
        } catch (Exception e){
            return e.getMessage();
        }
    }

}
