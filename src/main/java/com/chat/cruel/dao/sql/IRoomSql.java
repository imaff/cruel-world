package com.chat.cruel.dao.sql;

public interface IRoomSql {
    String getRoomById();

    String createNewRoom();

    String getMessageByRoom();

    String sendMessage();
}
