package com.example.elite_classroom.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elite_classroom.Activities.ClassActivity;
import com.example.elite_classroom.Activities.LoginActivity;
import com.example.elite_classroom.Adapter.ParticipantsAdapter;
import com.example.elite_classroom.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Objects;

public class PeopleFragment extends Fragment {
    View view;
    RecyclerView rvParticipants;
    ProgressBar progressBar;
    String class_code="", owner_code,class_name,owner_name;
    ImageView add_participants;
    String sharedPrefFile = "Login_Credentials";
    public static SharedPreferences preferences;



    TextView tvLoading, tvClassName, tvOwnerName;
    private String URL = "https://elite-classroom-server.herokuapp.com/api/classrooms/";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_people, container, false);

        class_code= getArguments().getString("class_code");
        owner_code= getArguments().getString("owner_id");
        class_name = getArguments().getString("class_name");
        owner_name = getArguments().getString("owner_name");

        add_participants = view.findViewById(R.id.add_participants);


        preferences = getContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);


        if(owner_code.equals(preferences.getString("google_token", null)))
        {
            add_participants.setVisibility(View.VISIBLE);
        }
        add_participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "You are Invited by "+owner_name+" To Join his/her class on Elite Classroom    \nThe class code for joining is : "+ class_code);
                try {
                    getContext().startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(),"Feature Unsupported",Toast.LENGTH_SHORT).show();
                }
            }
        });


        progressBar = view.findViewById(R.id.progressBar);
        tvLoading = view.findViewById(R.id.tvLoading);
        progressBar.setVisibility(View.VISIBLE);
        tvLoading.setVisibility(View.VISIBLE);
        tvClassName = view.findViewById(R.id.tvClassName);
        tvOwnerName = view.findViewById(R.id.tvOwnerName);
        rvParticipants = view.findViewById(R.id.rvParticipants);
        rvParticipants.setHasFixedSize(true);


        rvParticipants.setLayoutManager(new LinearLayoutManager(this.getActivity()));



                 ClassActivity.top_menu.setVisibility(View.GONE);
                 ClassActivity.top_menu_second.setVisibility(View.GONE);




        get_Students();


        return view;
    }



    private void get_Students() {



        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));


        URL += class_code;       // class_code

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");

                    String owner_id = data.getJSONObject("0").optString("owner_id");
                    tvOwnerName.setText(data.getJSONObject("0").optString("owner_name"));

                    JSONArray participants = data.getJSONArray("participants");
                    JSONArray participants_except_owner = new JSONArray();

                    for(int i=0; i<participants.length(); i++)
                    {
                        JSONObject object1 = (JSONObject) participants.get(i);
                        if(!(object1.optString("user_id").equals(owner_id)))
                            participants_except_owner.put(object1);


                    }
                    rvParticipants.setAdapter(new ParticipantsAdapter(getContext(), participants_except_owner));

                    progressBar.setVisibility(View.GONE);
                    tvLoading.setVisibility(View.GONE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                tvLoading.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
