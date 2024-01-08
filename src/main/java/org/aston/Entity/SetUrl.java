package org.aston.Entity;

import java.util.Date;

public class SetUrl {
    private String name;
    private String token;

    public SetUrl(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public SetUrl() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

