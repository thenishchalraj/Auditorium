package com.example.root.auditorium;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class req_details extends AppCompatActivity {


    FloatingActionButton all;
    FloatingActionButton na;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //showing the title of the current activity;
        setTitle("Request Details");

        setContentView(R.layout.activity_req_details);

        //definition of both floating buttons
        all = (FloatingActionButton)findViewById(R.id.button_allot);
        na = (FloatingActionButton)findViewById(R.id.button_not_allot);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Alloted !", Toast.LENGTH_SHORT).show();
            }
        });

        na.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Not Alloted !", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
