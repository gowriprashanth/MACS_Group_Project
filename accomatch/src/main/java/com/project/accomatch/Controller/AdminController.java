package com.project.accomatch.Controller;

import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.AdminInterface;
import com.project.accomatch.Service.Implementation.LeaseHolderDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    AdminInterface adminInterface;

    /**
     * Verifies a single ad
     * @author Yogish Honnadevipura Gopalakrishna
     * @return Success if verified or else Error
     */
    @PostMapping("/verify/one")
    public String verifySingleAd(@RequestBody Posts posts){
        return adminInterface.VerifyOneAd(posts);
    }

    /**
     * Verifies all unverified ads
     * @author Yogish Honnadevipura Gopalakrishna
     * @return Success if verified all or else Error
     */
    @PostMapping("/verify/all")
    public String verifyAllAd(@RequestBody Posts posts){
        return adminInterface.VerifyAllAd(posts);
    }
    @Autowired
    private LeaseHolderDashboardService dashboardService;

    /**
     * Retrieves the list of posts for Admin.
     * @author Ramandeep kaur
     * @return The list of posts.
     */
    @GetMapping("/get/list/post")
    public List<Posts> getListOfPosts() {
        return dashboardService.getListOfPosts();
    }

    /**
     * Retrieves the list of posts for Admin bases on status.
     * @author Ramandeep kaur
     * @return The list of posts.
     */
    @GetMapping("/get/list/post/{status}")
    public List<Posts> getListOfPostsByStatus(int status)
    {
        return dashboardService.getListOfPostsByStatus(status);
    }

}
