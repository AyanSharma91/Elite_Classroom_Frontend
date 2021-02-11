package com.example.elite_classroom.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.elite_classroom.PendingMissedAdapter;
import com.example.elite_classroom.PendingMissedModel;
import com.example.elite_classroom.R;
import com.example.elite_classroom.SubmittedModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class PendingFragment extends Fragment {

    View view;
    RecyclerView rvPending;
    private static final String URL = "https://elite-classroom-server.herokuapp.com/api/submission/notTurnedIn";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pending, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvPending = view.findViewById(R.id.rv_pending);
        rvPending.setHasFixedSize(true);
        rvPending.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("CODE ", response);

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                PendingMissedModel pendingAssignments = gson.fromJson(response, PendingMissedModel.class);

                rvPending.setAdapter(new PendingMissedAdapter(getContext(), pendingAssignments));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
}