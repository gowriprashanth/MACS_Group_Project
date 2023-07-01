package com.project.accomatch.security.Service;

import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Repository.UserTableOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserTableOperations userTableOperations;
  @Override
  public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
   /* User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
*/

    UserDetailsImpl userModel = new UserDetailsImpl();
    userModel.setEmail("raman@gmail.com");
    userTableOperations.loadUserByUsername(userModel);
    return UserDetailsImpl.build(userModel);


  }

}
