package com.project.accomatch.Controller;

import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Service.Implementation.MailSenderClass;
import com.project.accomatch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userservice;

    @Autowired
    private MailSenderClass mailSender;

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

            String mailID = model.getEmail();
            String result = userservice.CheckEmailID(mailID);
            if(Objects.equals(result, "ID exists")){
                String subject = "Password Reset";
                String body = "Click on the Below link to reset your Password";
                mailSender.sendEmail(model.getEmail(), subject, body);
                return "Mail Sent";
            }
            else{
                return result;
            }
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/updatepassword")
    public String updatePassword(@RequestBody UserModel model){
        try {
            return userservice.ForgotPassword(model);
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
