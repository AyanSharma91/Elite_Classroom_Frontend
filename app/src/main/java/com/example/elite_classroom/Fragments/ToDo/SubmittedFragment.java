package com.example.elite_classroom.Fragments.ToDo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elite_classroom.Adapter.SubmittedAdapter;
import com.example.elite_classroom.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Objects;


public class SubmittedFragment extends Fragment {

    View view;
    String sharedPrefFile = "Login_Credentials";
    SharedPreferences preferences;
    RecyclerView rvSubmitted;
    ProgressBar progressBar;
    TextView tvLoading;

    private String URL = "https://elite-classroom-server.herokuapp.com/api/todos/turnedIn/";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_submitted, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        tvLoading = view.findViewById(R.id.tvLoading);
        progressBar.setVisibility(View.VISIBLE);
        tvLoading.setVisibility(View.VISIBLE);
        rvSubmitted = view.findViewById(R.id.rv_submitted);
        rvSubmitted.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        preferences = getContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);


        getSubmittedAssignment();

        return view;
    }


    private void getSubmittedAssignment() {

        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL + preferences.getString("google_token",null), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONArray submittedAssignments= new JSONArray(response.get("data").toString());
                    rvSubmitted.setAdapter(new SubmittedAdapter(getContext(), submittedAssignments));
                    progressBar.setVisibility(View.GONE);
                    tvLoading.setVisibility(View.GONE);

                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
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