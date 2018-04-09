package com.example.root.auditorium;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.auditorium.Interface.MInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.root.auditorium.PojoClasses.ad_users;
import com.example.root.auditorium.PojoClasses.change_pass;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class add_user extends AppCompatActivity {

    EditText n;//name
    EditText u;//username
    EditText p;//password
    Button a;//add button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //changing the ActionBar
        setTitle("Add User");

        setContentView(R.layout.activity_add_user);

        n = (EditText)findViewById(R.id.new_name);
        u = (EditText)findViewById(R.id.new_username);
        p = (EditText)findViewById(R.id.new_password);
        a = (Button)findViewById(R.id.button_add_user);

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token1 = mSettings.getString("token","ttt");


        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {if(n.getText().toString().matches("") ||
                    u.getText().toString().matches("") ||
                    p.getText().toString().matches("") )
                Toast.makeText(getApplicationContext(),"Field Empty !",Toast.LENGTH_LONG).show();
            else
            {
                ad_users addnUser = new ad_users(n.getText().toString(),u.getText().toString(),p.getText().toString());
                Call<ad_users> call = MInterface.api.addNewUser(token1.replace("\"", ""), addnUser);

                call.enqueue(new Callback<ad_users>()
                {

                    @Override
                        public void onResponse(retrofit2.Call<ad_users> call, Response<ad_users> response) {

                        Log.d("TAG","====================="+new Gson().toJson(response.body()));
                             if(response.code()==200)
                                {
                                    Toast.makeText(getApplicationContext(),"User Added Successfully.",Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(add_user.this,admin_only.class);
                                    startActivity(i);
                                    finish();
                                }
                                else if(response.code()==500)
                                {
                                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_LONG).show();
                                }
                                else
                                    Toast.makeText(getApplicationContext(),"Bad Request",Toast.LENGTH_LONG).show();
                        }

                    @Override
                    public void onFailure(Call<ad_users> call, Throwable t) {

                    }
                });


                }

            }
        });
    }
}
