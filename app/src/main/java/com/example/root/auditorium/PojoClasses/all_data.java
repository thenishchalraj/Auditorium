package com.example.root.auditorium.PojoClasses;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by root on 3/27/18.
 */

@Generated("org.jsonschema2json")

public class all_data   {

    //@SerializedName("token") //this is needed if we are changing the json object name from the one that is defined in the api
    //@Expose

    //user
    private String id;

    private String name;

    private Boolean isAdmin;

    //auditorium
    private String _id;

    private String audi;

    //request
    private String req_id;

    private String dept;

    private String date;

    private String time;

    private String reason;

    private Boolean approval;


    //create constructor of the objects

    public all_data(String id, String name, String user, String pass,
                    Boolean isAdmin, String _id, String audi, String req_id,
                    String dept, String date, String time, String reason, Boolean approval) {
        this.id = id;
        this.name = name;
        this.isAdmin = isAdmin;
        this._id = _id;
        this.audi = audi;
        this.req_id = req_id;
        this.dept = dept;
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.approval = approval;
    }

    //create getter according to the objects you will get or set via api

    public String get_id() {
        return _id;
    }

    public String getAudi() {
        return audi;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    //and setter according to the objects you will get or set via api


}
