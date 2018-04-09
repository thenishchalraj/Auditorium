package com.example.root.auditorium.PojoClasses;

public class ad_request{

    private String name;
    private String dept;
    private String audi_id;
    private String reason;
   // private String created_by;
    private String date;
    private String stime;
    private String etime;
    private String created_by;

    public ad_request(String name, String dept, String audi_id, String reason,/* String created_by,*/ String date, String stime, String etime) {
        this.name = name;
        this.dept = dept;
        this.audi_id = audi_id;
        this.reason = reason;
      //  this.created_by = created_by;
        this.date = date;
        this.stime = stime;
        this.etime = etime;
    }

    public ad_request(String created_by) {
        this.created_by = created_by;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getAudi_id() {
        return audi_id;
    }

    public String getReason() {
        return reason;
    }

    //public String getCreated_by() {
      //  return created_by;
    //}

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
