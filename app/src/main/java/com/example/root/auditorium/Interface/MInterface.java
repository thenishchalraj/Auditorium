package com.example.root.auditorium.Interface;


import com.example.root.auditorium.PojoClasses.ad_audi;
import com.example.root.auditorium.PojoClasses.ad_request;
import com.example.root.auditorium.PojoClasses.ad_users;
import com.example.root.auditorium.PojoClasses.all_data;
import com.example.root.auditorium.PojoClasses.au_detail;
import com.example.root.auditorium.PojoClasses.auth_data;
import com.example.root.auditorium.PojoClasses.change_pass;
import com.example.root.auditorium.PojoClasses.d_audi;
import com.example.root.auditorium.PojoClasses.d_user;
import com.example.root.auditorium.PojoClasses.log_out;
import com.example.root.auditorium.PojoClasses.re_d;
import com.example.root.auditorium.PojoClasses.see_requests;
import com.example.root.auditorium.PojoClasses.user_data;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.Map;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by root on 3/27/18.
 */

public interface MInterface {

    String base_url = "http://drd00m.herokuapp.com/";  //base url which will prefix the requests below
/*
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    okhttp3.OkHttpClient.Builder httpClient = new okhttp3.OkHttpClient.Builder();
*/

    //creating retrofit object
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MInterface.base_url)
            //.client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //building the api
    MInterface api = retrofit.create(MInterface.class);


    /*@GET("/users/id") //get request to the server
    Call<List<all_data>> getUser(); //because we will receive a json array we will use list as the call and define the type of the list; return type method
    */

    //logging authorization
    @POST("auth/login")
    Call<auth_data> postAuth(@Body auth_data user_);

    //listing users for admin
    @GET("listuser")
    Call<List<user_data>> getUserList(@Header("x-auth-token") String token);

    //listing auditoriums for admin
    @retrofit2.http.GET("listaudi")
    Call<List<all_data>> getAudiList(@Header("x-auth-token") String token);

    //listing requests for admin
    @GET("allreq")
    Call<List<see_requests>> getRequestList(@Header("x-auth-token") String token);

    //changing the password
    @PUT("changepass")
    Call<change_pass> updatePass(@Header("x-auth-token") String token,@Body change_pass changepass );

    //log out
    @GET("logout")
    Call<log_out> getLogout(@Header("x-auth-token") String token);

    //adding user
    @POST("user")
    Call<ad_users> addNewUser(@Header("x-auth-token") String token, @Body ad_users addNUser);

    //adding auditorium
    @POST("audi")
    Call<ad_audi> addNewAudi(@Header("x-auth-token") String token, @Body ad_audi addNAudi);

    //adding request
    @POST("request")
    Call<ad_request> addNewRequest(@Header("x-auth-token") String token, @Body ad_request addNRequest);

    //allot request
    @PUT("request/{id}")
    Call<see_requests> allotRequest(@Header("x-auth-token") String token, @Path("id") String id);

    //view auditorium details
    @GET("/audi/{id}/requests")
    Call<List<au_detail>> getAudiDetail(@Header("x-auth-token") String token, @Path("id") String id);

    //view user details
    @GET ("user/{id}")
    Call<user_data> getUserDetail(@Header("x-auth-token") String token, @Path("id") String id);

    //view request details
    @GET("request/{id}")
    Call<re_d> getRequestDetail(@Header("x-auth-token") String token, @Path("id") String id);

    //delete user
    @DELETE("user/{id}")
    Call<d_user> delUser(@Header("x-auth-token") String token, @Path("id") String id);

    //delete auditorium
    @DELETE("audi/{id}")
    Call<d_audi> delAudi(@Header("x-auth-token") String token, @Path("id") String id);

    //delete request
    @DELETE("request/{id}")
    Call<see_requests> delReq(@Header("x-auth-token") String token, @Path("id") String id);

}