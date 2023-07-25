package com.project.accomatch.Service.Implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.accomatch.JWT.CustomUserDetailsService;
import com.project.accomatch.JWT.JwtService;
import com.project.accomatch.LoggerPack.LoggerClass;
import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Repository.Implementation.UserTableOperations;
import com.project.accomatch.Repository.UserTableOperationsInterface;
import com.project.accomatch.Service.AuthService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImplementation implements AuthService {
    private final UserTableOperations userDao;

    Logger logger = LoggerClass.getLogger();
    private final CustomUserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImplementation(
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
    UserTableOperationsInterface userTableOperations;

    /**
     * Signs up a new user and stores the user information in the database.
     * @author Yogish Honnadevipura Gopalakrishna
     * @param usermodel The UserModel object containing the user information to be stored in the database.
     * @return A string message indicating the status of the signup process ("Success" or "Error").
     */
    @Override
    public String SignUp(UserModel usermodel) {
        String encryptedPassword = passwordEncoder.encode(usermodel.getPassword());
        usermodel.setPassword(encryptedPassword);
        return userTableOperations.signUpUser(usermodel);
    }

    /**
     * Logs in a user by checking their email and password against the database.
     * @author Yogish Honnadevipura Gopalakrishna
     * @param usermodel The UserModel object containing the user's email and password for login.
     * @return A map containing user-related data and login status ("Success" or "Fail").
     */
    @Override
    public Map<String, String> Login(UserModel usermodel) {
       Map<String,String> map=new HashMap<>();
       logger.info("verifying User credentials for logging in!!");
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

    /**
     * Updates the user's password in the database after a password reset request.
     * @author Yogish Honnadevipura Gopalakrishna
     * @param usermodel The UserModel object containing the user's email and new password.
     * @return A string message indicating the status of the password update process ("Success" or "Error").
     */
    @Override
    public String ForgotPassword(UserModel usermodel) {
        usermodel.setPassword(passwordEncoder.encode(usermodel.getPassword()));
        return userTableOperations.ForgotPassword(usermodel);
    }

    /**
     * Checks if the provided email exists in the user table of the database.
     * @author Yogish Honnadevipura Gopalakrishna
     * @param mailID The email to be checked for existence.
     * @return A string message indicating the result of the email check ("Success" or "Fail").
     */
    @Override
    public String CheckEmailID(String mailID) {
        return userTableOperations.CheckMailID(mailID);
    }


}


