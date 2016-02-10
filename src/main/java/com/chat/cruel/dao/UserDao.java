package com.chat.cruel.dao;

import com.chat.cruel.beans.UserBeanAuth;
import com.chat.cruel.beans.UserBeanSession;
import com.chat.cruel.dao.sql.IUserSQl;
import com.chat.cruel.util.SaltedMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDao implements IUserDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private IUserSQl userSQl;

    @Override
    public UserBeanSession getUser(UserBeanAuth beanAuth) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("login", beanAuth.getLogin());
        String generatedPassword = null;
        try {
            generatedPassword = SaltedMD5.generate(beanAuth.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("password", generatedPassword);
        List<UserBeanSession> userBeanSessions = jdbcTemplate.query(userSQl.getUserSQL(), map, new RowMapper<UserBeanSession>() {
            @Override
            public UserBeanSession mapRow(ResultSet resultSet, int i) throws SQLException {
                return new UserBeanSession(resultSet.getString("login"), resultSet.getString("email"));
            }
        });
        return userBeanSessions.isEmpty()? null : userBeanSessions.get(0) ;
    }

    @Override
    public UserBeanSession registration(UserBeanAuth beanAuth) {
        if (getUser(beanAuth) != null) {
            return null;
        }
        String generatedPassword = null;
        try {
            generatedPassword = SaltedMD5.generate(beanAuth.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", System.currentTimeMillis());
        map.put("login", beanAuth.getLogin());
        map.put("email", beanAuth.getEmail());
        map.put("password", generatedPassword);
        jdbcTemplate.update(userSQl.registerUserSQL(), map);
        return getUser(beanAuth);
    }
}
