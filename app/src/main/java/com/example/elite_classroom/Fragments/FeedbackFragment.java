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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elite_classroom.Activities.MainActivity;
import com.example.elite_classroom.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class FeedbackFragment extends Fragment {

    View view;
    EditText etFeedback;
    Button submit_feedback;

    private String URL = "https://elite-classroom-server.herokuapp.com/api/feedback/submitFeedback";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        MainActivity.textView.setText("Feedback");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        etFeedback = view.findViewById(R.id.et_feedback);
        submit_feedback = view.findViewById(R.id.submit_feedback);


        submit_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = etFeedback.getText().toString().trim();

                if(feedback.isEmpty())
                    Toast.makeText(getActivity(), "Please enter something", Toast.LENGTH_SHORT).show();
                else
                {
                    RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));

                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("user_id","11");    // later replaced by user ID
                        obj.put("feedback_msg",feedback);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, obj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if(response.getInt("success") == 1)
                                    Toast.makeText(getActivity(), "Feedback Saved", Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

                    requestQueue.add(jsonObjectRequest);
                }
            }
        });
    }
}