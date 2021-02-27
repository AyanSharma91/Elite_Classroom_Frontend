package com.example.elite_classroom.Fragments.Teacher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elite_classroom.Activities.ClassActivity;
import com.example.elite_classroom.Activities.ClassWorkActivity;
import com.example.elite_classroom.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class NewAnnouncementFragment extends Fragment {
    EditText announcement_title,announcement_description;
    Button create_announcement;
    String class_code="", owner_code,class_name,owner_name;
    String token;
    String sharedPrefFile = "Login_Credentials";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newannouncement, container, false);



        class_code= getArguments().getString("class_code");
        owner_code= getArguments().getString("owner_id");
        class_name = getArguments().getString("class_name");
        owner_name = getArguments().getString("owner_name");

        SharedPreferences preferences = getActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        token = preferences.getString("google_token",null);

        ClassWorkActivity.attachment.setClickable(false);
        ClassWorkActivity.attachment.setVisibility(View.GONE);

        announcement_title = view.findViewById(R.id.announcement_title);
        announcement_description = view.findViewById(R.id.announcement_description);
        create_announcement = view.findViewById(R.id.create_announcement);

        create_announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = announcement_title.getText().toString();
                String description = announcement_description.getText().toString();
                if(title.isEmpty()){
                    announcement_title.setError("Please enter Title");
                    announcement_title.requestFocus();
                }
                else if(description.isEmpty()){
                    announcement_description.setError("Please enter Description");
                    announcement_description.requestFocus();
                }
                else{
                    announcement(title,description);
                }
            }
        });
        return view;
    }
    public void announcement(String title,String description){
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        String url = "https://elite-classroom-server.herokuapp.com/api/classworks/createClasswork";
        JSONObject o = new JSONObject();
        try {

            o.put("class_code", class_code);
            o.put("title", title);
            o.put("description", description);
            o.put("type",1);
            o.put("attachment","");
            o.put("due_date", "2021-02-22");
            o.put("google_token",token);
            o.put("points", "100");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, o, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent i = new Intent(getActivity(),ClassActivity.class);
                i.putExtra("class_code",class_code);
                i.putExtra("owner_id",owner_code);
                i.putExtra("class_name",class_name);
                i.putExtra("owner_name",owner_name);
                i.putExtra("from_Classwork",true);
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
