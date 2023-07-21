package com.project.accomatch.Repository;

import com.project.accomatch.Model.Applicant;

import java.util.List;

public interface LeaseApplicationRepositoryInterface {
    public List<Applicant> getListOfApplicant(int application_id);

}
