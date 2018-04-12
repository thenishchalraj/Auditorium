package com.example.root.auditorium;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.root.auditorium.Interface.MInterface;
import com.example.root.auditorium.PojoClasses.all_data;
import com.example.root.auditorium.PojoClasses.auth_data;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.root.auditorium.Interface.MInterface.api;


public class audi_list extends AppCompatActivity {

    FloatingActionButton req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String user = mSettings.getString("userwa","user");
        String token1 = mSettings.getString("token","ttt");

        //changing the ActionBar
        setTitle("Welcome " + user.replace("\"",""));
        setContentView(R.layout.activity_audi_list);

        req = (FloatingActionButton) findViewById(R.id.request_button);

        //defining a list view
        final ListView ll2 =(ListView)findViewById(R.id.audi_listview);
        Log.d("message2",token1);

        //calling the api
        Call<List<all_data>> call = api.getAudiList(token1.replace("\"", ""));

        call.enqueue(new Callback<List<all_data>>() {

            @Override
            public void onResponse(Call<List<all_data>> call, Response<List<all_data>> response) {

                Log.d("haha",response.toString());
                List<all_data> audis = response.body();

                //now, here make the items in the response list to be displayable in the list view made
                List<String> lst = new ArrayList<String>();
                //String[] lst = new String[audis.size()];
                for (int k = 0; k<audis.size(); k++){
                    lst.add(audis.get(k).getAudi());
                    //lst[k] = audis.get(k).getAudi();
                    Log.d("audiId", audis.get(k).get_id());

                    ll2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent j = new Intent(audi_list.this,audidetail.class);
                            String selectedFromList = (String) ll2.getItemAtPosition(i);
                            j.putExtra("audi_name",selectedFromList);
                            j.putExtra("audi_id",audis.get(i).get_id());
                            startActivity(j);
                        }
                    });

                }

                //defining and Declaring an ArrayAdapter to set items to ListView
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(audi_list.this, android.R.layout.simple_list_item_1, lst);

                ll2.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<all_data>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error while loading",Toast.LENGTH_SHORT).show();

                t.printStackTrace();

            }
        });

        //request for booking
        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent re=new Intent(audi_list.this,req_form.class);
                startActivity(re);
            }
        });


    }


    //to show and add the menu options in current activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.user_menu, menu);
        return true;
    }


    //to make the options in the menu workable
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id)
        {
            case R.id.change_pass_user:
                Intent cp = new Intent(audi_list.this, change_password.class);
                startActivity(cp);
                break;

            case R.id.logout_user:
                Intent l = new Intent(audi_list.this, login.class);
                startActivity(l);
                finish();
                break;

        }
        return true;
    }
}
