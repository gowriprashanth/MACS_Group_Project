package com.project.accomatch.Controller;

import com.project.accomatch.Model.ChatMessageModel;
import com.project.accomatch.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @PostMapping("/send")
    public String sendMessage(@RequestBody Map<String,Object> requestBody){
        try{
            int user_id = (Integer) requestBody.get("user_id");
            int room_id = (Integer) requestBody.get("room_id");
            String message = (String) requestBody.get("message");
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            ChatMessageModel chatMessageModel = new ChatMessageModel(message,room_id,user_id,currentTimestamp);
            return chatService.sendMessage(chatMessageModel);
        } catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getMessages")
    public ArrayList<ChatMessageModel> getMessages(@PathVariable int room_id){
        try{
            System.out.println(room_id);
            return chatService.getMessages(room_id);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
