package com.chat.cruel.dao.sql;

import org.springframework.stereotype.Component;

@Component
public class MysqlUserSQL implements IUserSQl {
    @Override
    public String getUserSQL() {
        return "SELECT id,login,email from users where login=:login and password=:password";
    }

    @Override
    public String registerUserSQL() {
        return "INSERT INTO users SET id=:id, login=:login, email=:email, password=:password";
    }
}
