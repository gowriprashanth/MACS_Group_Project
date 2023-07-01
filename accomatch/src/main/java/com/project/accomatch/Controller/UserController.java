package com.project.accomatch.Controller;

import com.project.accomatch.Model.UserModel;
import com.project.accomatch.Repository.UserTableOperations;
import com.project.accomatch.Service.Implementation.MailSenderClass;
import com.project.accomatch.Service.UserService;
import com.project.accomatch.payload.request.LoginRequest;
import com.project.accomatch.payload.request.SignupRequest;
import com.project.accomatch.payload.response.JwtResponse;
import com.project.accomatch.security.Service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.project.accomatch.security.JWT.JwtUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userservice;

    @Autowired
    private MailSenderClass mailSender;

    @Autowired
    private UserTableOperations userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getEmail(),
                null));
    }

    @PostMapping("/test/signup")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
       /* if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
*/
        // Create new user's account
        UserModel user = new UserModel(
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
       // Set<Role> roles = new HashSet<>();

      /*  if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
*/
       // user.setRoles(roles);
       // userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
    @PostMapping("/signup")
    public String signUp(@RequestBody UserModel model){
        return "success";
        //return userservice.SignUp(model);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserModel model){
        return "success";
      //  return userservice.Login(model);
    }

    @GetMapping("/login")
    public String login(){
        return "Login Successful";
    }

    @PostMapping("/forgotpassword")
    public String forgotPassword(@RequestBody UserModel model){
        try {

            String mailID = model.getEmail();
            String result = userservice.CheckEmailID(mailID);
            if(Objects.equals(result, "ID exists")){
                String subject = "Password Reset";
                String body = "Click on the Below link to reset your Password";
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

    @PostMapping("/updatepassword")
    public String updatePassword(@RequestBody UserModel model){
        try {
            return userservice.ForgotPassword(model);
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
