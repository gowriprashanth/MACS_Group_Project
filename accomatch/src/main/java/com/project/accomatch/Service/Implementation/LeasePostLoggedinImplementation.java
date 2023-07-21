package com.project.accomatch.Service.Implementation;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Repository.LeasePostsLoggedinRepository;
import com.project.accomatch.Service.LeasePostLoggedinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LeasePostLoggedinImplementation implements LeasePostLoggedinService {

    @Autowired
    LeasePostsLoggedinRepository leasePostsLoggedinRepository;
    public List<Posts> getListOfLoggedinApplicants(int application_id) {
        return leasePostsLoggedinRepository.getListOfLoggedinApplicant(application_id);
    }
}
