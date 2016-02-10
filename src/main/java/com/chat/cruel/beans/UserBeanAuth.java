package com.chat.cruel.beans;

public class UserBeanAuth extends UserBeanSession {
    private String password;

    public UserBeanAuth(String login, String password) {
        super(login);
        this.password = password;
    }
    public UserBeanAuth(String login, String email, String password) {
        super(login, email);
        this.password = password;
    }

    public UserBeanAuth(long id, String login, String email, String password) {
        super(id, login, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
