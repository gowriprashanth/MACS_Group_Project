package com.project.accomatch.Service.Implementation;

import com.project.accomatch.Model.ChatRoomModel;
import com.project.accomatch.Model.LeaseHolderApplicantModel;
import com.project.accomatch.Repository.Implementation.ApplyonPostTableOperations;
import com.project.accomatch.Repository.Implementation.ChatRoomOperations;
import com.project.accomatch.Repository.Implementation.LeaseHolderTableOperations;
import com.project.accomatch.Service.ApplyonPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyonPostServiceImplementation implements ApplyonPostService {
    @Autowired
    ApplyonPostTableOperations applyonPostTableOperations;
    @Autowired
    LeaseHolderTableOperations leaseHolderTableOperations;
    @Autowired
    ChatRoomOperations chatRoomOperations;
    @Override
    public String apply(LeaseHolderApplicantModel leaseHolderApplicantModel, ChatRoomModel chatRoomModel) {
        int leaser_holder_id= leaseHolderTableOperations.getLeaseHolderId(leaseHolderApplicantModel.getApplication_id());
        chatRoomModel.setUser_1_id(leaser_holder_id);
        int room_id = chatRoomOperations.createChatRoom(chatRoomModel);
        leaseHolderApplicantModel.setRoom_id(room_id);
        applyonPostTableOperations.applyOnPost(leaseHolderApplicantModel);
        return "Applied successfully";
    }

    @Override
    public boolean isApplied(LeaseHolderApplicantModel leaseHolderApplicantModel) {
        return applyonPostTableOperations.isAlreadyApplied(leaseHolderApplicantModel);
    }
}
