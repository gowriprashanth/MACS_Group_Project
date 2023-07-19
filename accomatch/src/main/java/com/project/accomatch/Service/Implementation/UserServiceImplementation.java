package com.project.accomatch.Service.Implementation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Repository.UserTableOperations;
import com.project.accomatch.Service.UserService;

import com.project.accomatch.config.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserTableOperations userTableOperations;
    @Override
    public String SignUp(UserModel usermodel) {
        return userTableOperations.signUpUser(usermodel);
    }

    @Override
    public Map<String, String> Login(UserModel usermodel) {
        Map<String,String> map =userTableOperations.LoginUser(usermodel);
        String token="";
       try{ if (map != null && map.get("Status").equals("Success")) {
           token = JwtUtil.generateToken(usermodel.getEmail());
       }
        map.put("token",token);
        return map;}
       catch(Exception e){
           System.out.print(e);
       }
       return map;
    }

    @Override
    public String ForgotPassword(UserModel usermodel) {
        return userTableOperations.ForgotPassword(usermodel);
    }

    @Override
    public String CheckEmailID(String mailID) {
        return userTableOperations.CheckMailID(mailID);
    }

    @Override
    public UserModel getUserInfo(int id) {
        return userTableOperations.getUserInfo(id);
    }

}


