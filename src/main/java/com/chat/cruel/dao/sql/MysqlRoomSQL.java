package com.chat.cruel.dao.sql;

import org.springframework.stereotype.Component;

@Component
public class MysqlRoomSQL implements IRoomSql {
    @Override
    public String getRoomById() {
        return "SELECT * FROM rooms where id=:id";
    }

    @Override
    public String createNewRoom() {
        return "INSERT INTO rooms SET id=:id, name=:id";
    }

    @Override
    public String getMessageByRoom() {
        return "SELECT login,message,date from history where room_id=:roomid order by date asc";
    }

    @Override
    public String sendMessage() {
        return "INSERT INTO history SET id=:id, login=:login, message=:message, room_id=:roomid, date=:date";
    }
}
