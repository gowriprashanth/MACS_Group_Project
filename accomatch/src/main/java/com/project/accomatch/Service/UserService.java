package com.project.accomatch.Service;

import com.project.accomatch.Model.UserModel;

public interface UserService {

        String SignUp(UserModel usermodel);
        String Login(UserModel usermodel);
        String ForgotPassword(UserModel usermodel);

}
