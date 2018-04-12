package com.example.root.auditorium;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.auditorium.Interface.MInterface;
import com.example.root.auditorium.PojoClasses.auth_data;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.FileReader;
import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.root.auditorium.Interface.MInterface.*;

public class login extends Activity {

    Button l; //login button
    EditText u; //username field
    EditText p; //password field
    TextView m;

  //  SharedPreferences logIn;
  //  SharedPreferences isAdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);


        l=(Button)findViewById(R.id.button);
        u=(EditText)findViewById(R.id.editText);
        p=(EditText)findViewById(R.id.editText3);
        m=(TextView)findViewById(R.id.login_message);

/*
        //defining shared preference for logged in users
        logIn = getSharedPreferences("login",MODE_PRIVATE);
        isAdm = getSharedPreferences("isAdmin",MODE_PRIVATE);


        if(logIn.getBoolean("logged",true)){
           if(isAdm.getBoolean("isAdmin",true))
           {
               Intent k = new Intent(login.this,admin_only.class);
               startActivity(k);
           }
           else
           {
               Intent k = new Intent(login.this,audi_list.class);
               startActivity(k);
           }

        }

*/

        //defining shared preferences
            SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = mSettings.edit();

        //Gson instance
            Gson gson = new Gson();



        //tapping on login button
            l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    auth_data user_ = new auth_data(u.getText().toString() , p.getText().toString() );
                    Call<auth_data> call = MInterface.api.postAuth(user_);

                    call.enqueue(new Callback<auth_data>() {
                        @Override
                        public void onResponse(Call<auth_data> call, Response<auth_data> response) {

                            Log.d("response",response.toString());
                            //401-unauthorized,403-forbidden
                            if(response.code()==200 || response.code()==201)
                            {
                                String tokenResponse = gson.toJson(response.body());
                                JsonParser parser = new JsonParser();
                                JsonElement jsonTree = parser.parse(tokenResponse);
                                JsonObject jsonObject = jsonTree.getAsJsonObject();
                                JsonElement token = jsonObject.get("token");
                                editor.putString("userwa",u.getText().toString());
                                editor.putString("token",token.toString());
                                editor.apply();

                                Toast.makeText(getApplicationContext(),"Logging in...",Toast.LENGTH_SHORT).show();

                                if(response.code()==201)
                                {
                                   /* isAdm.edit().putBoolean("isAdmin",true).apply();
                                    logIn.edit().putBoolean("logged",true).apply();
                                   */ Intent i = new Intent(login.this, admin_only.class);
                                    startActivity(i);
                                    finish();
                                }
                                else
                                {

                                   /* isAdm.edit().putBoolean("isAdmin",false).apply();
                                    logIn.edit().putBoolean("logged",true).apply();
                                    */ Intent i = new Intent(login.this, audi_list.class);
                                    startActivity(i);
                                    finish();
                                }

                            }
                                else
                                    {
                                        if(response.code()==403 )
                                            m.setText("Check username/password !");
                                        else
                                            if(response.code()==401)
                                            m.setText("Unauthorized Error !");
                                    }
                            }




                        @Override
                        public void onFailure(Call<auth_data> call, Throwable t) {

                            Toast.makeText(getApplicationContext(),"no connection",Toast.LENGTH_LONG).show();
                        }
                    });
              }
            });



    }
}
