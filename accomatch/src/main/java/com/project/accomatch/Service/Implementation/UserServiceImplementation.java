package com.project.accomatch.Service.Implementation;

import com.project.accomatch.LoggerPack.LoggerClass;
import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Repository.Implementation.UserTableOperations;
import com.project.accomatch.Repository.UserTableOperationsInterface;
import com.project.accomatch.Service.UserService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    private UserTableOperationsInterface userTableOperations;

    Logger logger = LoggerClass.getLogger();
    /**
     * Retrieves user information from the database based on the user's ID.
     * @author Yogish Honnadevipura Gopalakrishna
     * @param id The ID of the user whose information is to be retrieved.
     * @return The UserModel object containing the user information, or an empty UserModel object if not found.
     */
    @Override
    public UserModel getUserInfo(int id) {
        return userTableOperations.getUserInfo(id);
    }

}
