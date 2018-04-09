package com.example.root.auditorium.PojoClasses;

public class re_d {

    private String name;
    private String dept;
    private String reason;
    private String date;
    private String stime;
    private String etime;
    private String audi_name;

    public re_d(String name, String dept, String reason, String date, String stime, String etime, String audi_name) {
        this.name = name;
        this.dept = dept;
        this.reason = reason;
        this.date = date;
        this.stime = stime;
        this.etime = etime;
        this.audi_name = audi_name;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getReason() {
        return reason;
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

    public String getAudi_name() {
        return audi_name;
    }
}
