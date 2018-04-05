package com.example.root.auditorium;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.auditorium.PojoClasses.all_data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.root.auditorium.Interface.MInterface.api;

/**
 * Created by root on 3/21/18.
 */



public class view_audi extends Fragment {
    private static final String TAG = "activity_view_audi";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_audi, container, false);

        final ListView ll2 =(ListView)view.findViewById(R.id.audi_list);



        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String token1 = mSettings.getString("token","ttt");

        //calling the api
        Call<List<all_data>> call = api.getAudiList(token1.replace("\"", ""));

        call.enqueue(new Callback<List<all_data>>() {

            @Override
            public void onResponse(Call<List<all_data>> call, Response<List<all_data>> response) {

                List<all_data> audis = response.body();

                //now, here make the items in the response list to be displayable in the list view made
                String[] lst = new String[audis.size()];
                for (int k = 0; k<audis.size(); k++){
                    lst[k] = audis.get(k).getAudi();

                    //lst.add(lst.get(k));

/*                    ll2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent j = new Intent(audi_list.this,audidetail.class);

                            String selectedFromList = (String) ll2.getItemAtPosition(i);

                            j.putExtra("audi_name",selectedFromList);
                            startActivity(j);
                        }
                    });
*/
                }

                //defining and Declaring an ArrayAdapter to set items to ListView
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lst);

                ll2.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<all_data>> call, Throwable t) {
                Toast.makeText(getActivity(),"Error while loading",Toast.LENGTH_SHORT).show();

                t.printStackTrace();

            }
        });

        return view;

    }
}
