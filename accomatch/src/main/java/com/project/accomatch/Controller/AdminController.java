package com.project.accomatch.Controller;

import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminInterface adminInterface;

    @PostMapping("/admin/verifyone")
    public String verifySingleAd(@RequestBody Posts posts){
        return adminInterface. VerifyOneAd(posts);
    }

    @PostMapping("/admin/verifyall")
    public String verifyAllAd(@RequestBody Posts posts){
        return adminInterface.VerifyAllAd(posts);
    }
}
