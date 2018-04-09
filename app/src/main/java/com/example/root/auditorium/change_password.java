package com.example.root.auditorium;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.root.auditorium.Interface.MInterface;
import com.example.root.auditorium.PojoClasses.change_pass;
import retrofit2.Callback;
import retrofit2.Response;

public class change_password extends AppCompatActivity {

    EditText cp;
    EditText np;
    EditText cnp;
    Button  up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //changing the ActionBar
        setTitle("Change Password");

        setContentView(R.layout.activity_change_password);

        cp = (EditText)findViewById(R.id.curr_pass);
        np = (EditText)findViewById(R.id.new_pass);
        cnp = (EditText)findViewById(R.id.con_new_pass);
        up = (Button)findViewById(R.id.update_pass);


        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token1 = mSettings.getString("token","ttt");


        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(cp.getText().toString().equals(np.getText().toString()))
                    Toast.makeText(getApplicationContext(),"New Password can't be same as Current Password !",Toast.LENGTH_LONG).show();
                if(cp.getText().toString().equals(cnp.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Wrong confirmation of password! Please re-enter ",Toast.LENGTH_LONG).show();

                else
                    if(np.getText().toString().equals(cnp.getText().toString()))
                    {

                        change_pass changePass = new change_pass(cp.getText().toString(),np.getText().toString(),cnp.getText().toString());
                        retrofit2.Call<change_pass> call = MInterface.api.updatePass(token1.replace("\"", ""),changePass);

                        call.enqueue(new Callback<change_pass>() {
                            @Override
                            public void onResponse(retrofit2.Call<change_pass> call, Response<change_pass> response) {

                                if(response.code()==200 || response.code()==201){

                                    Toast.makeText(getApplicationContext(),"Password Changed Successfully",Toast.LENGTH_LONG).show();

                                    if(response.code()==201)
                                    {
                                        //Toast.makeText(getApplicationContext(),"Password Changed Successfully",Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(change_password.this,admin_only.class);
                                        startActivity(i);
                                        finish();
                                    }
                                    else
                                    {
                                        Intent i = new Intent(change_password.this,audi_list.class);
                                        startActivity(i);
                                        finish();
                                    }

                                }
                                    else if(response.code()==401)
                                    {
                                        Toast.makeText(getApplicationContext(),"Current Password is WRONG !!",Toast.LENGTH_LONG).show();
                                    }
                                    else Toast.makeText(getApplicationContext(),"Internal Server Error !!",Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(retrofit2.Call<change_pass> call, Throwable t) {

                            }
                        });


                    }

            }
        });


    }
}
