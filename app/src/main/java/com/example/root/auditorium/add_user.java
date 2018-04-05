package com.example.root.auditorium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class add_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //changing the ActionBar
        setTitle("Add User");

        setContentView(R.layout.activity_add_user);

    }
}
