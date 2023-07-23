package com.project.accomatch.Controller;

import com.project.accomatch.LoggerPack.LoggerClass;
import com.project.accomatch.Model.LeaseHolderModel;
import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Service.Implementation.CreateApplicationFactory;
import com.project.accomatch.Service.Implementation.MailSenderClass;
import com.project.accomatch.Service.LeaseHolderService;
import com.project.accomatch.Service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/application")
public class CreateApplicationController {
    @Autowired
    private CreateApplicationFactory createApplicationService;
    Logger logger = LoggerClass.getLogger();

    @PostMapping("/create")
    public String createAD(@RequestBody Map<String, Object> requestBody) {
        try {
            return createApplicationService.createAD(requestBody);
        } catch (Exception e) {
            logger.error("An error occurred while creating the application: {}", e.getMessage());
            return e.getMessage();
        }
    }

}
