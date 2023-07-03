package com.project.accomatch.Service;

import com.project.accomatch.Model.HouseSeekerModel;
import com.project.accomatch.Repository.Applicant;

import java.util.List;

public interface HouseSeekerService {
    String createAD(HouseSeekerModel houseSeekerModel);

    List<HouseSeekerModel> getListOfAllApplicantPosts();
}
