package com.project.accomatch.Controller;


import com.project.accomatch.Exception.UserNotFoundException;
import com.project.accomatch.Service.Implementation.UserServiceImplementation;
import com.project.accomatch.LoggerPack.LoggerClass;
import com.project.accomatch.Model.UserModel;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImplementation userservice;
    Logger logger = LoggerClass.getLogger();

    /**
     * Endpoint to retrieve user information by user ID.
     * @author Yogish Honnadevipura Gopalakrishna
     * @param id The user ID whose information is to be retrieved.
     * @return The UserModel object containing user information.
     */
    @GetMapping("/get/{id}")
    public UserModel getUserInformation(@PathVariable int id){
        try{
            if(id < 1){
                throw new UserNotFoundException("User ID cannot be 0 or negative");
            }
            logger.info("get user information controller active");
            return userservice.getUserInfo(id);
        }catch (UserNotFoundException u){
            logger.error("user not found");
            return null;
        }

    }

}
