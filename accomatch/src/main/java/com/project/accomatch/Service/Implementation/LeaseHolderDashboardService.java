package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Model.Posts;
import com.project.accomatch.Repository.LeaseholderAdsDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseHolderDashboardService {

    public List<Posts> getListOfPosts() {
        LeaseholderAdsDao leaseholderAdsDao = new LeaseholderAdsDao();
        return leaseholderAdsDao.getListOfPosts();
    }
}
