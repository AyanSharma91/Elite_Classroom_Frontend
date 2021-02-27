package com.example.elite_classroom.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.elite_classroom.Activities.ClassActivity;
import com.example.elite_classroom.Adapter.StreamAdapter;
import com.example.elite_classroom.Models.Recycler_Models.Stream;
import com.example.elite_classroom.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StreamFragment extends Fragment {
    List<Stream> list;
    StreamAdapter adapter;
    RecyclerView recyclerView;
    TextView class_name, owner_name;
    Context ctx;
    String token;
    String sharedPrefFile = "Login_Credentials";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stream, container, false);

        String class_names =getArguments().getString("class_name");
        String owner_names = getArguments().getString("owner_name");

        class_name= view.findViewById(R.id.class_name);
        owner_name= view.findViewById(R.id.owner_name);

        class_name.setText(class_names);
        owner_name.setText(owner_names);
        ctx=getActivity();

        SharedPreferences preferences = getActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        token = preferences.getString("google_token",null);

        list=new ArrayList<>();

        recyclerView=view.findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));

        String url = "https://elite-classroom-server.herokuapp.com/api/notes/getNotesCode/"+ ClassActivity.classCode;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++){
                        JSONObject o = response.getJSONObject(i);
                        if(o.getString("class_code").equals(ClassActivity.classCode)){
                            Stream l = new Stream(
                                    o.getString("notes_id"),
                                    o.getString("class_code"),
                                    o.getString("attachment_id"),
                                    o.getString("posted_on"),
                                    o.getString("title"),
                                    o.getString("description"),
                                    o.getString("owner_token")
                            );
                            list.add(l);
                        }
                    }
                    adapter = new StreamAdapter(list,ctx,token);
                    recyclerView.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
        return view;
    }
}
