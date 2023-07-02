package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Model.Posts;
import com.project.accomatch.Repository.LeaseholderAdsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseHolderDashboardService {

    @Autowired
    LeaseholderAdsDao leaseholderAdsDao;
    public List<Posts> getListOfPosts() {
        return leaseholderAdsDao.getListOfPosts();
    }
}
