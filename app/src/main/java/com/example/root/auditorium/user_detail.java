package com.example.root.auditorium;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

public class user_detail extends AppCompatActivity {

    FloatingActionButton fdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setting activity title
        setTitle("User Detail");

        setContentView(R.layout.activity_user_detail);

        fdb = (FloatingActionButton)findViewById(R.id.float_del_user_button);

        fdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"User Removed",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
