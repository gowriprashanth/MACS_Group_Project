package com.project.accomatch.Controller;

import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Service.UserService;
import com.project.accomatch.mail.MailSenderClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userservice;

    @Autowired
    public MailSenderClass mailSender;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserModel model){
        return userservice.SignUp(model);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserModel model){
         return userservice.Login(model);
    }

    @PostMapping("/forgotpassword")
    public String forgotPassword(@RequestBody UserModel model){
        try {

            String subject = "Password Reset";
            String body = "Click on the Below link to reset your Password";
            mailSender.sendEmail(model.getEmail(), "Test Subject", "Test Body");
            return "Mail Sent";
//            return userservice.ForgotPassword(model);
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/updatepassword")
    public void updatePassword(@RequestBody UserModel model){
        try {
            userservice.ForgotPassword(model);
        }catch(Exception e){
            return;
        }
    }
}
