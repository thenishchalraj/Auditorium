package com.example.root.auditorium.PojoClasses;

public class see_requests {

    private String _id;
    private String name;
    private String dept;
    private String date;

    public see_requests(String _id, String name, String dept, String date) {
        this._id = _id;
        this.name = name;
        this.dept = dept;
        this.date = date;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getDate() {
        return date;
    }
}
