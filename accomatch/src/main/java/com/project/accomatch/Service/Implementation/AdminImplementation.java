package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Model.Posts;
import com.project.accomatch.Repository.AdminRepository;
import com.project.accomatch.Service.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminImplementation implements AdminInterface {

    @Autowired
    AdminRepository adminRepository;
    @Override
    public String VerifyOneAd(Posts posts) {
        return adminRepository.OneAd(posts);
    }

    @Override
    public String VerifyAllAd(Posts posts) {
        return adminRepository.AllAd(posts);
    }

}
