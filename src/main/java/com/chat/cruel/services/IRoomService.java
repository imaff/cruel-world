package com.chat.cruel.services;

import com.chat.cruel.beans.Message;
import com.chat.cruel.beans.Room;

import java.util.List;

public interface IRoomService {
    Room getRoomById(long id);

    Room createNewRoom();

    List<Message> getMessagesByRoom(long id);

    Message sendMessage(Message message);
}
