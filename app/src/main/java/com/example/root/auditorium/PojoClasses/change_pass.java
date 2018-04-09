package com.example.root.auditorium.PojoClasses;

public class change_pass {
    //change password
    private String currpass;
    private String newpass1;
    private String newpass2;

    public change_pass(String currpass, String newpass1, String newpass2) {
        this.currpass = currpass;
        this.newpass1 = newpass1;
        this.newpass2 = newpass2;
    }

    public String getCurrpass() {
        return currpass;
    }

    public String getNewpass1() {
        return newpass1;
    }

    public String getNewpass2() {
        return newpass2;
    }
/*    public void setCurrpass(String currpass) {
        this.currpass = currpass;
    }

    public void setNewpass1(String newpass1) {
        this.newpass1 = newpass1;
    }

    public void setNewpass2(String newpass2) {
        this.newpass2 = newpass2;
    }*/
}
