package com.example.root.auditorium;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.root.auditorium.PojoClasses.all_data;
import com.example.root.auditorium.PojoClasses.d_audi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.root.auditorium.Interface.MInterface.api;

public class del_audi extends AppCompatActivity {

    FloatingActionButton fdab;
    Spinner s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //changing the ActionBar
        setTitle("Delete Auditorium");

        setContentView(R.layout.activity_del_audi);

        fdab = (FloatingActionButton)findViewById(R.id.float_del_audi_button);
        s1 = (Spinner)findViewById(R.id.audi_spinner);
        s1.setPrompt("Make Selection");

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token1 = mSettings.getString("token","ttt");
        final String[] idd = new String[1];

        //calling the api
        Call<List<all_data>> call = api.getAudiList(token1.replace("\"", ""));

        call.enqueue(new Callback<List<all_data>>() {
            @Override
            public void onResponse(Call<List<all_data>> call, Response<List<all_data>> response) {

                List<all_data> audis = response.body();

                //now, here make the items in the response list to be displayable in the list view made
                List<String> list = new ArrayList<String>();
                for (int k = 0; k<audis.size(); k++)
                {
                    list.add(audis.get(k).getAudi());

                    s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            idd[0] = audis.get(i).get_id();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            return;
                        }
                    });

                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s1.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<all_data>> call, Throwable t) {

            }
        });

        fdab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<d_audi> call2 = api.delAudi(token1.replace("\"",""),idd[0].replace("\"",""));
                call2.enqueue(new Callback<d_audi>() {
                    @Override
                    public void onResponse(Call<d_audi> call, Response<d_audi> response) {

                        if(response.code()!=200)
                            Toast.makeText(getApplicationContext(),"Some Error Occurred",Toast.LENGTH_SHORT).show();
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Removed",Toast.LENGTH_SHORT).show();
                            Intent k = new Intent(del_audi.this,admin_only.class);
                            startActivity(k);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<d_audi> call, Throwable t) {

                    }
                });
            }
        });
    }
}
