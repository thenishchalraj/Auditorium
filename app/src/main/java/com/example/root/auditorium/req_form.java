package com.example.root.auditorium;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.root.auditorium.PojoClasses.ad_request;
import com.example.root.auditorium.PojoClasses.all_data;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.DatePickerDialog.*;
import static com.example.root.auditorium.Interface.MInterface.api;

public class req_form extends AppCompatActivity implements View.OnClickListener {

    EditText n;//name
    EditText de;//department
    Spinner as;//auditorium selector
    EditText r;//reason
    Button d;//done
    //-------------------------------
    Button bd;//button set date
    Button bst; //button set stime
    Button bet; //button set etime
    TextView da;//date
    TextView ft;//from time
    TextView tt;//to time
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //changing action bar name
        setTitle("Request Space");
        setContentView(R.layout.activity_req_form);

        n = (EditText)findViewById(R.id.edit_name);
        de = (EditText)findViewById(R.id.edit_department);
        da = (TextView)findViewById(R.id.edit_date);
        ft = (TextView)findViewById(R.id.edit_from_time);
        tt = (TextView)findViewById(R.id.edit_to_time);
        as = (Spinner)findViewById(R.id.audi_selector);
        r = (EditText)findViewById(R.id.edit_reason);
        d=(Button)findViewById(R.id.done_button);
        bd = (Button)findViewById(R.id.button_date);
        bst = (Button)findViewById(R.id.button_stime);
        bet = (Button)findViewById(R.id.button_etime);

//        bd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //get current date
//                final Calendar c = Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(this, OnDateSetListener(){
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//
//                        da.setText(i2 + "-" + (i1 + 1) + "-" + i);
//                    }
//                }, mYear, mMonth, mDay);
//
//                datePickerDialog.show();
//            }
//        });

        bd.setOnClickListener((View.OnClickListener) this);
        bst.setOnClickListener((View.OnClickListener) this);
        bet.setOnClickListener((View.OnClickListener) this);

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token1 = mSettings.getString("token","ttt");
        SharedPreferences.Editor editor = mSettings.edit();

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

                    as.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                as.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<all_data>> call, Throwable t) {

            }
        });

        //button to send request
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n.getText().toString().matches("") ||
                        de.getText().toString().matches("")||
                        da.getText().toString().matches("")||
                        ft.getText().toString().matches("")||
                        tt.getText().toString().matches("")||
                        r.getText().toString().matches(""))
                    Toast.makeText(getApplicationContext(),"Enter Proper Details",Toast.LENGTH_LONG).show();
                else
                    {
                        ad_request addnRequest = new ad_request(n.getText().toString(),de.getText().toString(),
                                idd[0].replace("\"",""),r.getText().toString(),da.getText().toString(),
                                ft.getText().toString(),tt.getText().toString());
                        Call<ad_request> call2 = api.addNewRequest(token1.replace("\"", ""),addnRequest);
                        call2.enqueue(new Callback<ad_request>() {
                            @Override
                            public void onResponse(Call<ad_request> call, Response<ad_request> response) {

                                if(response.isSuccessful())
                                {
//                                    if(response.code()!=200)
//                                    {
//                                        Log.d("SEERESPONSE",response.body().toString());
//                                        Toast.makeText(getApplicationContext(),"Can't send request !",Toast.LENGTH_LONG).show();
//                                    }
//                                    else
//                                    {
                                    Gson g = new Gson();
                                    String tokenResponse = g.toJson(response.body());
                                    Log.d("faklnfjkg",tokenResponse);
                                    JsonParser parser = new JsonParser();
                                    JsonElement jsonTree = parser.parse(tokenResponse);
                                    JsonObject jsonObject = jsonTree.getAsJsonObject();
                                    JsonElement userwa = jsonObject.get("created_by");
                                    editor.putString("userwa",userwa.toString());
                                    editor.apply();

                                        if(response.code()==201)
                                        {
                                            Toast.makeText(getApplicationContext(), "All Done", Toast.LENGTH_SHORT).show();
                                            Intent j = new Intent(req_form.this, admin_only.class);
                                            startActivity(j);
                                            finish();
                                        }
                                       else
                                            {
                                                Toast.makeText(getApplicationContext(), "All Done", Toast.LENGTH_SHORT).show();
                                                Intent j = new Intent(req_form.this, audi_list.class);
                                                startActivity(j);
                                                finish();
                                            }


                                    }
//                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Request Failed",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ad_request> call, Throwable t) {

                            }
                        });
                    }
            }
        });
    }

    @Override
    public void onClick(View view)
    {

        if (view == bd) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            da.setText(year+ "/" + (monthOfYear + 1) +"/"+dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (view == bst) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            ft.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (view == bet) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            tt.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

    }
}
