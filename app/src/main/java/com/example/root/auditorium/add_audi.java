package com.example.root.auditorium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class add_audi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //change title in action bar
        setTitle("Add Auditorium");
        setContentView(R.layout.activity_add_audi);
    }
}
