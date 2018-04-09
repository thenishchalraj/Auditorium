package com.example.root.auditorium.PojoClasses;

public class au_detail {

    private String dept;
    private String date;
    private String stime;
    private String etime;

    public au_detail(String dept, String date, String stime, String etime) {
        this.dept = dept;
        this.date = date;
        this.stime = stime;
        this.etime = etime;
    }

    public String getDept() {
        return dept;
    }

    public String getDate() {
        return date;
    }

    public String getStime() {
        return stime;
    }

    public String getEtime() {
        return etime;
    }
}
