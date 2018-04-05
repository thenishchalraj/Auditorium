package com.example.root.auditorium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class req_form extends AppCompatActivity {

    Button d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //changing action bar name
        setTitle("Request Space");
        setContentView(R.layout.activity_req_form);

        d=(Button)findViewById(R.id.done_button);

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "All Done", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
