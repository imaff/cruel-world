package com.chat.cruel.services;

import com.chat.cruel.beans.Message;
import com.chat.cruel.beans.Room;
import com.chat.cruel.dao.IRoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomService implements IRoomService {
    @Autowired
    private IRoomDao roomDao;

    @Override
    public Room getRoomById(long id) {
        return roomDao.getRoomById(id);
    }

    @Override
    public Room createNewRoom() {
        return roomDao.createNewRoom();
    }

    @Override
    public List<Message> getMessagesByRoom(long id) {
        return roomDao.getMessagesByRoom(id);
    }

    @Override
    public Message sendMessage(Message message) {
        return roomDao.sendMessage(message);
    }
}
