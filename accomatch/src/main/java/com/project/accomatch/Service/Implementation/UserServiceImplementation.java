package com.project.accomatch.Service.Implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.accomatch.JWT.CustomUserDetails;
import com.project.accomatch.JWT.CustomUserDetailsService;
import com.project.accomatch.JWT.JwtService;
import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Repository.UserTableOperations;
import com.project.accomatch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImplementation implements UserService {
    private final UserTableOperations userDao;

    private final CustomUserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImplementation(
            UserTableOperations userDao, CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userDao = userDao;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    @Autowired
    UserTableOperations userTableOperations;
    @Override
    public String SignUp(UserModel usermodel) {
        String encryptedPassword = passwordEncoder.encode(usermodel.getPassword());
        usermodel.setPassword(encryptedPassword);
        return userTableOperations.signUpUser(usermodel);
    }

    @Override
    public Map<String, String> Login(UserModel usermodel) {
       Map<String,String> map=new HashMap<>();
       String authToken="";
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usermodel.getEmail(), usermodel.getPassword()));
         UserDetails currentUser = this.userDetailsService.loadUserByUsername(usermodel.getEmail());

        if (currentUser != null) {
             authToken = jwtService.generateToken(currentUser);
        }

           // map= userTableOperations.LoginUser(usermodel);

        map.put("token",authToken);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String currentUserJSON = objectMapper.writeValueAsString(currentUser);
            map.put("userDetails", String.valueOf((currentUserJSON)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    @Override
    public String ForgotPassword(UserModel usermodel) {

        usermodel.setPassword(passwordEncoder.encode(usermodel.getPassword()));
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


