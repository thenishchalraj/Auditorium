package com.example.root.auditorium;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class del_audi extends AppCompatActivity {

    FloatingActionButton fdab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //changing the ActionBar
        setTitle("Delete Auditorium");

        setContentView(R.layout.activity_del_audi);

        fdab = (FloatingActionButton)findViewById(R.id.float_del_audi_button);

        fdab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Removed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
