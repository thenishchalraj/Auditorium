package com.example.root.auditorium;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.auditorium.PojoClasses.re_d;
import com.example.root.auditorium.PojoClasses.see_requests;
import com.example.root.auditorium.PojoClasses.user_data;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.root.auditorium.Interface.MInterface.api;

public class req_details extends AppCompatActivity {

    TextView n;//name
    TextView dep;//department
    TextView da;//date
    TextView t;//time
    TextView a;//auditorium
    TextView re;//reason
    FloatingActionButton all;
    FloatingActionButton na;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle r = getIntent().getExtras();
        String ri = r.getString("req_id");

        //showing the title of the current activity;
        setTitle("Request Details");

        setContentView(R.layout.activity_req_details);

        //definition of both floating buttons
        all = (FloatingActionButton)findViewById(R.id.button_allot);
        na = (FloatingActionButton)findViewById(R.id.button_not_allot);
        n = (TextView)findViewById(R.id.text_name);
        dep = (TextView)findViewById(R.id.text_dept);
        da = (TextView)findViewById(R.id.text_date);
        t = (TextView)findViewById(R.id.text_time);
        a = (TextView)findViewById(R.id.text_requested_audi);
        re = (TextView)findViewById(R.id.text_reason);

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token1 = mSettings.getString("token","ttt");

        //calling the api
        Call<re_d> call = api.getRequestDetail(token1.replace("\"", ""),ri.replace("\"",""));

        call.enqueue(new Callback<re_d>() {
            @Override
            public void onResponse(Call<re_d> call, Response<re_d> response) {
                Gson g = new Gson();
                String userResponse = g.toJson(response.body());
                JsonParser parser = new JsonParser();
                JsonElement jsonT = parser.parse(userResponse);
                JsonObject jsonO = jsonT.getAsJsonObject();
                JsonElement nam = jsonO.get("name");
                JsonElement dp = jsonO.get("dept");
                JsonElement rea = jsonO.get("reason");
                JsonElement dat = jsonO.get("date");
                JsonElement sti = jsonO.get("stime");
                JsonElement eti = jsonO.get("etime");
                JsonElement aud = jsonO.get("audi_name");
                n.setText(String.valueOf(nam).replace("\"",""));
                dep.setText(String.valueOf(dp).replace("\"",""));
                re.setText(String.valueOf(rea).replace("\"",""));
                da.setText(String.valueOf(dat).replace("\"",""));
                t.setText((String.valueOf(sti).replace("\"",""))+ " - " +(String.valueOf(eti).replace("\"","")));
                a.setText(String.valueOf(aud).replace("\"",""));
            }

            @Override
            public void onFailure(Call<re_d> call, Throwable t) {

            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<see_requests> call3 = api.allotRequest(token1.replace("\"", ""),ri.replace("\"",""));
                call3.enqueue(new Callback<see_requests>() {
                    @Override
                    public void onResponse(Call<see_requests> call, Response<see_requests> response) {
                        if(response.code()!=200)
                            Toast.makeText(getApplicationContext(), "Some error occurred while alloting !", Toast.LENGTH_LONG).show();
                        else{
                            Toast.makeText(getApplicationContext(), "Alloted!", Toast.LENGTH_SHORT).show();
                            Intent m = new Intent(req_details.this, admin_only.class);
                            startActivity(m);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<see_requests> call, Throwable t) {

                    }
                });
            }
        });

        na.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<see_requests> call2 = api.delReq(token1.replace("\"", ""),ri.replace("\"",""));
                call2.enqueue(new Callback<see_requests>() {
                    @Override
                    public void onResponse(Call<see_requests> call, Response<see_requests> response) {

                        if(response.code()!=200)
                        Toast.makeText(getApplicationContext(), "Some error occurred while removing request !", Toast.LENGTH_LONG).show();
                        else{
                            Toast.makeText(getApplicationContext(), "Request removed!", Toast.LENGTH_SHORT).show();
                            Intent l = new Intent(req_details.this, admin_only.class);
                            startActivity(l);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<see_requests> call, Throwable t) {

                    }
                });
            }
        });


    }
}
