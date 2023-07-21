package com.project.accomatch.Service.Implementation;
import com.project.accomatch.Model.Posts;
import com.project.accomatch.Repository.Implementation.LeasePostsLoggedinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LeasePostLoggedinService {

    @Autowired
    LeasePostsLoggedinRepository leasePostsLoggedinRepository;
    public List<Posts> getListOfLoggedinApplicants(int application_id) {
        return leasePostsLoggedinRepository.getListOfLoggedinApplicant(application_id);
    }
}
