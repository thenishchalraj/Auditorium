package com.example.root.auditorium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class a_room extends AppCompatActivity {

    Button a_r;
    EditText e_r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_room);

        e_r=(EditText)findViewById(R.id.room);
        a_r=(Button)findViewById(R.id.add_r);

    }
}
