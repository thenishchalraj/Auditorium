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

    //auditorium
    private String _id;
    private String audi;

    //create constructor of the objects
    public all_data(String _id, String audi) {                 //auditorium
        //auditorium
        this._id = _id;
        this.audi = audi;
    }

    //create getter according to the objects you will get or set via api

    //auditorium
    public String get_id() {
        return _id;
    }
    public String getAudi() {
        return audi;
    }

    //and setter according to the objects you will get or set via api


}
