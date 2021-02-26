package com.example.elite_classroom.Fragments.Teacher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elite_classroom.Activities.ClassActivity;
import com.example.elite_classroom.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class NewMaterialFragment extends Fragment {
    EditText material_title,material_description;
    Button create_material;
    String title,description="",token;
    String sharedPrefFile = "Login_Credentials";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newmaterial, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        token = preferences.getString("google_token",null);
        material_title = view.findViewById(R.id.material_title);
        material_description = view.findViewById(R.id.material_description);
        create_material = view.findViewById(R.id.create_material);
        create_material.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       title = material_title.getText().toString();
                       description = material_description.getText().toString();
                       if(title.isEmpty()){
                           material_title.setError("Please enter Title");
                           material_title.requestFocus();
                       }else{
                           createMaterial(title,description);
                       }
                    }
                });
        return view;
    }
    public void createMaterial(String title,String description){
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        String url = "https://elite-classroom-server.herokuapp.com/api/notes/createNotes"+ ClassActivity.classCode;
        JSONObject o = new JSONObject();
        try {
            o.put("title",title);
            o.put("description",description);
            o.put("attachment_id",null);
            o.put("google_token",token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, o, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent i = new Intent(getActivity(),ClassActivity.class);
                startActivity(i);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }
}
