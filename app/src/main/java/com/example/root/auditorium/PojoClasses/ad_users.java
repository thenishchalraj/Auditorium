package com.example.root.auditorium.PojoClasses;

import java.util.HashMap;
import java.util.Map;

public class ad_users {
    //change password
    private String name;
    private String user;
    private String pass;

    public ad_users(String name, String user, String pass) {
        this.name = name;
        this.user = user;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

}
