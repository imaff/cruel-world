package com.chat.cruel.beans;

public class UserBeanSession {
    private long id;
    private String login;
    private String email;

    public UserBeanSession(String login) {
        this.id = 0;
        this.login = login;
        this.email = "";
    }

    public UserBeanSession(String login, String email) {
        this.id = 0;
        this.login = login;
        this.email = email;
    }

    public UserBeanSession(long id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }
}
