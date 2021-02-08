package com.example.elite_classroom.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elite_classroom.Activities.MainActivity;
import com.example.elite_classroom.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Objects;

public class CreateClassFragment extends Fragment {
    EditText et,et1,et2,et3;
    String class_name,prof_id,section,room,subject;
    Button b;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createclass, container, false);
        et = view.findViewById(R.id.class_name);
        et1 = view.findViewById(R.id.prof_id);
//        et2 = view.findViewById(R.id.room);
//        et3 = view.findViewById(R.id.subject);
        b = view.findViewById(R.id.btn_create);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Please give Class Name",Toast.LENGTH_SHORT).show();
                }
                else if(et1.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Please give Professor Id",Toast.LENGTH_SHORT).show();
                }
                else{
                    class_name = et.getText().toString();
                    prof_id = et1.getText().toString();
//                    section = et1.getText().toString();
//                    room = et2.getText().toString();
//                    subject = et3.getText().toString();
                    createClass(class_name,prof_id);
                }
            }
        });
        return view;
    }
    public void createClass(String class_name,String prof_id){
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        String url = "https://elite-classroom-backend.herokuapp.com/create";
        JSONObject o = new JSONObject();
        try{
        o.put("class_name",class_name);
        o.put("prof_id",prof_id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, o, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Toast.makeText(getActivity(),"Class created Successfully",Toast.LENGTH_SHORT).show();
            Fragment someFragment = new ClassFragment();
            assert getFragmentManager() != null;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, someFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });
    requestQueue.add(request);
    }
}
