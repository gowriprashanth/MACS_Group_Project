package com.project.accomatch.Controller;

import com.project.accomatch.Exception.InvalidInputException;
import com.project.accomatch.Service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/room")
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomService;
    @PostMapping("/getRoomId/")
    public int getRoomId(@RequestBody Map<String,Object> requestBody){
        int user_id = (Integer) requestBody.get("user_id");
        int application_id = (Integer) requestBody.get("application_id");
        if (application_id <= 0 || user_id<=0) {
            throw new InvalidInputException("Invalid application ID provided.");
        }
        return chatRoomService.getRoomId(application_id,application_id);
    }
}
