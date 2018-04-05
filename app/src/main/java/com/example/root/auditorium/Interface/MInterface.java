package com.example.root.auditorium.Interface;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.root.auditorium.PojoClasses.all_data;
import com.example.root.auditorium.PojoClasses.auth_data;
import com.example.root.auditorium.PojoClasses.change_pass;
import com.example.root.auditorium.login;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by root on 3/27/18.
 */

public interface MInterface {

    String base_url = "http://drd00m.herokuapp.com/";  //base url which will prefix the requests below


    //creating retrofit object
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MInterface.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //building the api
    MInterface api = retrofit.create(MInterface.class);


    /*@GET("/users/id") //get request to the server
    Call<List<all_data>> getUser(); //because we will receive a json array we will use list as the call and define the type of the list; return type method
    */

    //logging authorization
    @POST("auth/login")
    Call<auth_data> postAuth( @Body auth_data user_);

    //listing users for admin
    @GET("listuser")
    Call<List<all_data>> getUserList(@Header("x-auth-token") String token);

    //listing auditoriums for admin
    @retrofit2.http.GET("listaudi")
    Call<List<all_data>> getAudiList(@Header("x-auth-token") String token);

    //changing the password
    @PUT("changepass")
    Call<List<change_pass>> updatePass(@Header("x-auth-token") String token,@Body change_pass changepass );

}