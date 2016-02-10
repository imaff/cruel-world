package com.chat.cruel.dao;

import com.chat.cruel.beans.Message;
import com.chat.cruel.beans.Room;

import java.util.List;

public interface IRoomDao {
    Room getRoomById(long id);

    Room createNewRoom();

    List<Message> getMessagesByRoom(long id);

    Message sendMessage(Message message);
}
