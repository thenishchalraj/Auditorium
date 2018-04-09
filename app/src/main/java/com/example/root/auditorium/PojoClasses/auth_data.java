package com.example.root.auditorium.PojoClasses;

import android.content.SharedPreferences;

import javax.annotation.Generated;

@Generated("org.jsonschema2json")

public class auth_data {

    //username and password
    private String user;
    private String pass;

    public auth_data(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    //token
    private String token;

    public auth_data(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
