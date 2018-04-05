package com.example.root.auditorium;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.ArrayList;

/**
 * Created by root on 3/21/18.
 */



public class view_requests extends Fragment {
    private static final String TAG = "activity_view_requests";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_requests, container, false);

        ListView ll1 =(ListView)view.findViewById(R.id.request_list);

        // Items entered by the user is stored in this ArrayList variable
        ArrayList<String> lst = new ArrayList<String>();

        final int N = 50; // total number of textviews to add

        for (int k = 0; k<= N; k++){
            lst.add("Request #" + k);

            ll1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent j = new Intent(getActivity(), req_details.class);
                    startActivity(j);
                }
            });

        }


        //defining and Declaring an ArrayAdapter to set items to ListView
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lst);

        ll1.setAdapter(adapter);

        return view;

    }

}
