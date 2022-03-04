package com.unicauca.activate.mapper;

import com.unicauca.activate.model.User;

public class JsonResponseLogin {
    private String token;
    private User user;
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    
}
