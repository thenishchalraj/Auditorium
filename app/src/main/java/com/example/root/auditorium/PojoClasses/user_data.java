package com.example.root.auditorium.PojoClasses;

public class user_data {

    //user
    private String _id;
    private String name;
    private Boolean isAdmin;
    private String user;

    public user_data(String _id, String name, Boolean isAdmin) {
        this._id = _id;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public user_data(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

}
