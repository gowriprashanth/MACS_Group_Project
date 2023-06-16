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
    UserService userservice;

    @Autowired
    MailSenderClass mailSender;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserModel model){
        return userservice.SignUp(model);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserModel model){
        try {
            return userservice.Login(model);
        }catch(Exception e){
            return e.getMessage();
        }

    }

    @PostMapping("/forgotpassword")
    public String forgotPassword(@RequestBody UserModel model){
        try {
            return userservice.ForgotPassword(model);
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/sendemail")
    public void sendEmail(){
        try {
            mailSender.sendEmail("shreyasbalajin@gmail.com", "Test Subject", "Test Body");
        }catch(Exception e){
            return;
        }
    }
}
