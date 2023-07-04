package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Model.Posts;
import com.project.accomatch.Repository.ApplicantPostFilteringOperation;
import com.project.accomatch.Service.ApplicantPostFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApplicantPostFilterImplementation implements ApplicantPostFilterService {

    @Autowired
    ApplicantPostFilteringOperation applicantPostFilteringOperation;
    @Override
    public List<Posts> filterPost(Map<String, String> jsonMap) {
        return applicantPostFilteringOperation.filterPosts(jsonMap);
    }
}
