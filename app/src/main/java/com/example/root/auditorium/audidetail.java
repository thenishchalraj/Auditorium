package com.example.root.auditorium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class audidetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //getting auditorium name from audi list
        Bundle a = getIntent().getExtras();
        String au = a.getString("audi_name");

        //changing the ActionBar
        setTitle(au);

        setContentView(R.layout.activity_audidetail);
    }
}
