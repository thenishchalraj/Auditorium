package com.example.root.auditorium;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.root.auditorium.PojoClasses.all_data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.root.auditorium.Interface.MInterface.api;

/**
 * Created by root on 3/21/18.
 */



public class view_user extends Fragment {
    private static final String TAG = "activity_view_user";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_user, container, false);

        ListView ll = (ListView)view.findViewById(R.id.user_listview);

        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String token1 = mSettings.getString("token","ttt");

        //calling the api
        Call<List<all_data>> call = api.getUserList(token1.replace("\"", ""));

        call.enqueue(new Callback<List<all_data>>() {
            @Override
            public void onResponse(Call<List<all_data>> call, Response<List<all_data>> response) {
                List<all_data> users = response.body();

                //now, here make the items in the response list to be displayable in the list view made
                String[] lst = new String[users.size()];
                for (int k = 0; k<users.size(); k++) {
                    lst[k] = users.get(k).getName();
                }

                //defining and Declaring an ArrayAdapter to set items to ListView
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lst);

                ll.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<all_data>> call, Throwable t) {

            }
        });

      /*  // Items entered by the user is stored in this ArrayList variable
        ArrayList<String> lst = new ArrayList<String>();

        final int N = 50; // total number of textviews to add

        for (int k = 0; k <= N; k++){
            lst.add("Username #" + k);

            ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent j = new Intent(getActivity(), user_detail.class);
                    startActivity(j);
                }
            });

        }*/
        return view;

    }
}
