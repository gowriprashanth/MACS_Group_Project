package com.project.accomatch.Controller;

import com.project.accomatch.Exception.InvalidPostStatusException;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Service.AdminInterface;
import com.project.accomatch.Service.Implementation.LeaseHolderDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    AdminInterface adminInterface;
    @Autowired
    private LeaseHolderDashboardService dashboardService;

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


    /**
     * Retrieves the list of posts for Admin.
     * @author Ramandeep kaur
     * @return The list of posts.
     */
    @GetMapping("/get/list/post")
    public List<Posts> getListOfPosts()  {
        return dashboardService.getListOfPosts();
    }

    /**
     * Retrieves the list of posts for Admin bases on status.
     * @author Ramandeep kaur
     * @return The list of posts.
     */
    @PostMapping("/get/list/postbystatus")
    public List<Posts> getListOfPostsByStatus(@RequestBody HashMap<String, String> map) {
        int status;
        try {
            status = Integer.parseInt(map.get("status"));
        } catch (NumberFormatException e) {
            throw new InvalidPostStatusException("Invalid post status value");
        }
        return dashboardService.getListOfPostsByStatus(status);
    }

}
