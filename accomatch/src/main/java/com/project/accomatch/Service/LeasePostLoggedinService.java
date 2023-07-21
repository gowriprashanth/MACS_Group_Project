package com.project.accomatch.Service;

import com.project.accomatch.Model.Posts;

import java.util.List;

public interface LeasePostLoggedinService {
    public List<Posts> getListOfLoggedinApplicants(int application_id);
}
