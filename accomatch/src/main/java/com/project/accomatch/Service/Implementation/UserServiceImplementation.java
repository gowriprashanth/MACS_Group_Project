package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Repository.UserRepository;
import com.project.accomatch.Repository.UserTableOperations;
import com.project.accomatch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTableOperations userTableOperations;
    @Override
    public String SignUp(UserModel usermodel) {
//        userRepository.save(usermodel);
        return userTableOperations.signUpUser(usermodel);
    }

    @Override
    public String Login(UserModel usermodel) {
        return userTableOperations.LoginUser(usermodel);
    }

    @Override
    public String ForgotPassword(UserModel usermodel) {
        return userTableOperations.ForgotPassword(usermodel);
    }

}

