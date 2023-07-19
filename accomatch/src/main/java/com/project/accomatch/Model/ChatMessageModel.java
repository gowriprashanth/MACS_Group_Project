package com.project.accomatch.Model;

import java.sql.Time;

public class ChatMessageModel {
    private String message;
    private int room_id,user_id;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }



    public ChatMessageModel(String message, int room_id, int user_id) {
        this.message = message;
        this.room_id = room_id;
        this.user_id = user_id;
    }
}
