package com.project.accomatch.Controller;

import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userservice;

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

}
