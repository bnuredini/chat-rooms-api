package com.example.chatrooms;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(String msg) {
        super(msg);
    }
}
