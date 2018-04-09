package com.example.root.auditorium;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.root.auditorium.PojoClasses.all_data;
import com.example.root.auditorium.PojoClasses.au_detail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.root.auditorium.Interface.MInterface.api;

public class audidetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //getting auditorium name from audi list
        Bundle a = getIntent().getExtras();
        String au = a.getString("audi_name");
        String ai = a.getString("audi_id");

        //changing the ActionBar
        setTitle(au);

        setContentView(R.layout.activity_audidetail);

        //defining a list view
        final ListView ll4 =(ListView)findViewById(R.id.audi_detail_listview);

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token1 = mSettings.getString("token","ttt");

        //calling the api
        Call<List<au_detail>> call = api.getAudiDetail(token1.replace("\"", ""),ai.replace("\"",""));

        call.enqueue(new Callback<List<au_detail>>() {
            @Override
            public void onResponse(Call<List<au_detail>> call, Response<List<au_detail>> response) {

                List<au_detail> audisD = response.body();
                //now, here make the items in the response list to be displayable in the list view made
                String[] lst = new String[audisD.size()];
                for (int k = 0; k<audisD.size(); k++)
                    lst[k] = audisD.get(k).getDept() + " | " +audisD.get(k).getDate() +" | " + audisD.get(k).getStime()+" | " +audisD.get(k).getEtime();

                //defining and Declaring an ArrayAdapter to set items to ListView
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(audidetail.this, android.R.layout.simple_list_item_1, lst);

                ll4.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<au_detail>> call, Throwable t) {

            }
        });


    }
}
