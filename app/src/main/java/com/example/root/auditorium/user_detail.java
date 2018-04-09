package com.example.root.auditorium;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.root.auditorium.PojoClasses.au_detail;
import com.example.root.auditorium.PojoClasses.d_user;
import com.example.root.auditorium.PojoClasses.user_data;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.root.auditorium.Interface.MInterface.api;

public class user_detail extends AppCompatActivity {

    FloatingActionButton fdb;
    TextView u;
    TextView n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getting auditorium name from audi list
        Bundle b = getIntent().getExtras();
        String ui = b.getString("audi_id");


        //setting activity title
        setTitle("User Detail");

        setContentView(R.layout.activity_user_detail);

        u = (TextView)findViewById(R.id.textView4);
        n = (TextView)findViewById(R.id.textView5);
        fdb = (FloatingActionButton)findViewById(R.id.float_del_user_button);

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token1 = mSettings.getString("token","ttt");

        //calling the api
        Call<user_data> call = api.getUserDetail(token1.replace("\"", ""),ui.replace("\"",""));

        call.enqueue(new Callback<user_data>() {
            @Override
            public void onResponse(Call<user_data> call, Response<user_data> response) {

                Gson g = new Gson();
                String userResponse = g.toJson(response.body());
                JsonParser parser = new JsonParser();
                JsonElement jsonT = parser.parse(userResponse);
                JsonObject jsonO = jsonT.getAsJsonObject();
                JsonElement us = jsonO.get("user");
                JsonElement na = jsonO.get("name");
                u.setText(String.valueOf(us).replace("\"",""));
                n.setText(String.valueOf(na).replace("\"",""));

            }

            @Override
            public void onFailure(Call<user_data> call, Throwable t) {

            }
        });

        fdb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Call<d_user> call2 = api.delUser(token1.replace("\"",""),ui.replace("\"",""));
                call2.enqueue(new Callback<d_user>() {
                    @Override
                    public void onResponse(Call<d_user> call, Response<d_user> response) {

                        if(response.code()!=200)
                            Toast.makeText(getApplicationContext(),"Can't Remove User",Toast.LENGTH_SHORT).show();
                        else
                        {
                            Toast.makeText(getApplicationContext(),"User Removed",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(user_detail.this,admin_only.class);
                            startActivity(i);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<d_user> call, Throwable t) {

                    }
                });

            }
        });

    }
}
