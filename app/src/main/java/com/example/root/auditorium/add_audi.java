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
import com.example.root.auditorium.PojoClasses.ad_audi;
import com.example.root.auditorium.PojoClasses.ad_users;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_audi extends AppCompatActivity {

    EditText ad;
    Button a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //change title in action bar
        setTitle("Add Auditorium");
        setContentView(R.layout.activity_add_audi);

        ad = (EditText)findViewById(R.id.add_audit);
        a = (Button)findViewById(R.id.button_add_audi);

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token1 = mSettings.getString("token","ttt");


        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {if(ad.getText().toString().matches(""))
                Toast.makeText(getApplicationContext(),"Field Empty !",Toast.LENGTH_LONG).show();
            else
            {
                ad_audi addnAudi = new ad_audi(ad.getText().toString());
                Call<ad_audi> call = MInterface.api.addNewAudi(token1.replace("\"", ""), addnAudi);

                call.enqueue(new Callback<ad_audi>()
                {

                    @Override
                    public void onResponse(retrofit2.Call<ad_audi> call, Response<ad_audi> response) {

                        Log.d("TAG","====================="+new Gson().toJson(response.body()));
                        if(response.code()==200)
                        {
                            Toast.makeText(getApplicationContext(),"Added Successfully.",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(add_audi.this,admin_only.class);
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
                    public void onFailure(Call<ad_audi> call, Throwable t) {

                    }
                });


            }

            }
        });

    }
}
