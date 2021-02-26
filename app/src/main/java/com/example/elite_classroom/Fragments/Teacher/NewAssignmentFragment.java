package com.example.elite_classroom.Fragments.Teacher;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.elite_classroom.Dialogs.PointDialog;
import com.example.elite_classroom.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

public class NewAssignmentFragment extends Fragment implements PointDialog.PointDialogListener {
    EditText assignment_title,assignment_description,point,due_date;
    Button crete_assignment;
    Calendar myCalendar;
    String title,description="",points="100",due="No due date",token;
    String sharedPrefFile = "Login_Credentials";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newassignment, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        token= preferences.getString("google_token",null);
        //DatePicker
        myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        assignment_title = view.findViewById(R.id.assignment_title);
        assignment_description = view.findViewById(R.id.assignment_description);
        point = view.findViewById(R.id.point);
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        due_date = view.findViewById(R.id.due_date);
        due_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        crete_assignment = view.findViewById(R.id.crete_assignment);
        crete_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = assignment_title.getText().toString();
                description = assignment_description.getText().toString();
                points = point.getText().toString();
                due = due_date.getText().toString();
                if(title.isEmpty()){
                    assignment_title.setError("Please enter Title");
                    assignment_title.requestFocus();
                }
                else{
                    createAssignment(title,description,points,due);
                }
            }
        });
        return view;
    }
    private void updateLabel() {
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");

        date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String time1 = date.format(myCalendar.getTime());
        due_date.setText(time1);
    }
    public void openDialog() {
        PointDialog pointDialog = new PointDialog();
        assert getFragmentManager() != null;
        pointDialog.show(getFragmentManager(), "point dialog");
    }
    public void createAssignment(String title,String description,String points,String due){
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        String url = "https://elite-classroom-server.herokuapp.com/api/classrooms/createClasswork";
        JSONObject o = new JSONObject();
        try {
            o.put("class_code", ClassActivity.classCode);
            o.put("title", title);
            o.put("description", description);
            o.put("type",0);
            o.put("attachment",null);
            o.put("due_date", due);
            o.put("google_token",token);
            o.put("points", points);
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

    @Override
    public void applyTexts(String points) {
        Log.i("point",points);
       point.setText(points);
    }
}
