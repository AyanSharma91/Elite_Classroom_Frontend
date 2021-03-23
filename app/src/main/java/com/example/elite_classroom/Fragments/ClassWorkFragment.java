package com.example.elite_classroom.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.example.elite_classroom.Adapter.ClassWorkAdapter;
import com.example.elite_classroom.Dialogs.ClassWorkBottomSheetDialog;
import com.example.elite_classroom.Models.Recycler_Models.ClassWork;
import com.example.elite_classroom.Models.Recycler_Models.Stream;
import com.example.elite_classroom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassWorkFragment extends Fragment {
    List<ClassWork> list;
    ClassWorkAdapter adapter;
    RecyclerView recyclerView;
    Context ctx;
    String class_code="", owner_code,class_name,owner_name;
    String sharedPrefFile = "Login_Credentials",token;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classwork, container, false);



       class_code= getArguments().getString("class_code");
       owner_code= getArguments().getString("owner_id");
       class_name = getArguments().getString("class_name");
       owner_name = getArguments().getString("owner_name");

       Bundle b = new Bundle();
       b.putString("class_code",class_code);
       b.putString("owner_id",owner_code);
       b.putString("class_name",class_name);
       b.putString("owner_name",owner_name);



        SharedPreferences preferences = getActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        token = preferences.getString("google_token",null);

        FloatingActionButton buttonAddNote = view.findViewById(R.id.class_bottom);
        if(token.equals(owner_code)){
            buttonAddNote.setClickable(false);
            buttonAddNote.setVisibility(View.VISIBLE);
        }
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassWorkBottomSheetDialog bottomSheet = new ClassWorkBottomSheetDialog();
                bottomSheet.setArguments(b);
                bottomSheet.show(getFragmentManager(), "ClassWorkBottomSheet");
            }
        });
        ctx=getActivity();
        list=new ArrayList<>();
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        String url = "https://elite-classroom-server.herokuapp.com/api/classworks/getClasswork/"+ class_code;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++){
                        JSONObject o = response.getJSONObject(i);
                        if(o.getString("class_code").equals(ClassActivity.classCode)){

                            ClassWork l = new ClassWork(
                                    o.getString("work_id"),
                                    o.getString("class_code"),
                                    o.getString("title"),
                                    o.getString("description"),
                                    o.getInt("type"),
                                    o.getString("attachment"),
                                    o.getString("created_date"),
                                    o.getString("due_date"),
                                    o.getString("points"),
                                    o.getString("owner_token")


                            );
                            list.add(l);
                        }
                    }


                    adapter = new ClassWorkAdapter(list,ctx,token);
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
