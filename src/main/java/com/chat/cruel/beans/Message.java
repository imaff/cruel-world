package com.chat.cruel.beans;

import java.util.Date;

public class Message {
    public String login;
    public String message;
    public long roomid;
    public Date date;
    public String stringDate;

    public Message(String login, String message, Date date) {
        this.login = login;
        this.message = message;
        this.date = date;
    }

    public Message(String login, String message, Long roomid) {
        this.login = login;
        this.message = message;
        this.roomid = roomid;
    }

    public Message(String login, String message, String date) {
        this.login = login;
        this.message = message;
        this.stringDate = date;
    }
}
