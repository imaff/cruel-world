package com.chat.cruel.dao;

import com.chat.cruel.beans.Message;
import com.chat.cruel.beans.Room;
import com.chat.cruel.dao.sql.IRoomSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class RoomDao implements IRoomDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private IRoomSql roomSql;

    @Override
    public Room getRoomById(long id) {
        List<Room> rooms = jdbcTemplate.query(roomSql.getRoomById(), Collections.singletonMap("id", id), new RowMapper<Room>() {
            @Override
            public Room mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Room(resultSet.getString("id"), resultSet.getString("name"));
            }
        });
        return rooms.isEmpty() ? null : rooms.get(0);
    }

    @Override
    public Room createNewRoom() {
        long id = System.currentTimeMillis();
        jdbcTemplate.update(roomSql.createNewRoom(),Collections.singletonMap("id",id));
        return getRoomById(id);
    }

    @Override
    public List<Message> getMessagesByRoom(long id) {
        List<Message> messages = jdbcTemplate.query(roomSql.getMessageByRoom(), Collections.singletonMap("roomid", id), new RowMapper<Message>() {
            @Override
            public Message mapRow(ResultSet resultSet, int i) throws SQLException {
                String date = resultSet.getString("date");
                return new Message(resultSet.getString("login"), resultSet.getString("message"), date);
            }
        });
        return messages;
    }


    @Override
    public Message sendMessage(Message message) {
        Map<String,Object> map = new HashMap<String, Object>();
        long id = System.currentTimeMillis();
        map.put("id", id);
        map.put("login", message.login);
        map.put("message", message.message);
        map.put("roomid",message.roomid);
        map.put("date",new Date());
        jdbcTemplate.update(roomSql.sendMessage(),map);
        message.date = new Date(id);
        return message;
    }
}
