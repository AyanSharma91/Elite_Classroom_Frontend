package com.example.elite_classroom.Fragments.ToDo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.Objects;


public class SubmittedFragment extends Fragment {

    View view;
    RecyclerView rvSubmitted;
    ProgressBar progressBar;
    TextView tvLoading;

    private String URL = "https://elite-classroom-server.herokuapp.com/api/todos/turnedIn/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_submitted, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        tvLoading = view.findViewById(R.id.tvLoading);
        progressBar.setVisibility(View.VISIBLE);
        tvLoading.setVisibility(View.VISIBLE);
        rvSubmitted = view.findViewById(R.id.rv_submitted);
        rvSubmitted.setHasFixedSize(true);
        rvSubmitted.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));

        URL = URL + "token1";   // later to be replaced by current user token

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONArray submittedAssignments= new JSONArray(response.get("data").toString());
                    rvSubmitted.setAdapter(new SubmittedAdapter(getContext(), submittedAssignments));
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
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show(); }
        });

        requestQueue.add(jsonObjectRequest);
    }
}