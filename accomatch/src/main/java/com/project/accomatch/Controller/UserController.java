package com.project.accomatch.Controller;

import com.project.accomatch.HasherClass;
import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Service.Implementation.MailSenderClass;
import com.project.accomatch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
@CrossOrigin("http://localhost:3000")
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
    public Map<String, String> login(@RequestBody UserModel model){
       return userservice.Login(model);
    }

    @GetMapping("/login")
    public String login(){
        return "Login Successful";
    }

    @GetMapping("/get/{id}")
    public UserModel getUserInformation(@PathVariable int id){
        return userservice.getUserInfo(id);
    }

    @PostMapping("/forgot/password")
    public String forgotPassword(@RequestBody UserModel model){
        try {
            System.out.println(model.getEmail());
            String mailID = model.getEmail();
            String email = HasherClass.hashEmail(mailID);
            String result = userservice.CheckEmailID(mailID);
            if(Objects.equals(result, "Success")){
                String subject = "Password Reset";
                String body = "Click on the Below link to reset your Password\n\n" +
                        "http://localhost:3000/updatepassword?email=" + email;
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

    @PostMapping("/update/password")
    public String updatePassword(@RequestBody UserModel model){
        try {
            model.setEmail(HasherClass.unHashEmail(model.getEmail()));
            return userservice.ForgotPassword(model);
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
