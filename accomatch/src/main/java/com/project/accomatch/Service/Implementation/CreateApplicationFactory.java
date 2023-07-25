package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Service.HouseSeekerService;
import com.project.accomatch.Service.LeaseHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CreateApplicationFactory {

    @Autowired
    LeaseHolderService leaseHolderService;

    @Autowired
    HouseSeekerService houseSeekerService;

    public String createAD(Map<String,Object> requestBody){
        try{
            if(requestBody.get("type").equals("AP")) {
                houseSeekerService.createAD(requestBody);
            }else if(requestBody.get("type").equals("LH"))
                leaseHolderService.createAD(requestBody);
            return "Success";
        }catch(Exception e){
            return "Error";
        }


    }

}
