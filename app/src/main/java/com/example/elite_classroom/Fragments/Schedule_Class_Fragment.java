package com.example.elite_classroom.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.elite_classroom.Activities.ClassWorkActivity;
import com.example.elite_classroom.Models.Retrofit_Models.Auth_Responses;
import com.example.elite_classroom.Models.Retrofit_Models.Google_Logins;
import com.example.elite_classroom.Models.Retrofit_Models.Schedule_Class_Request;
import com.example.elite_classroom.Models.Retrofit_Models.Schedule_Class_Response;
import com.example.elite_classroom.R;
import com.example.elite_classroom.Retrofit.DestinationService;
import com.example.elite_classroom.Retrofit.ServiceBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;

public class Schedule_Class_Fragment extends Fragment {


    AppCompatEditText class_link;
    AppCompatEditText class_description;
    TextView select_date;
    Calendar myCalendar;
    TextView select_time;
    ImageView schedule_button,reschedule_button;
    String classcode="";
    Window window;
    ImageView cross;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule__class_, container, false);
        cross = view.findViewById(R.id.cross);

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new Owner_Create_Class(),"Owner_FRAGMENT").commit();
            }
        });


        window= getActivity().getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.getDecorView().getWindowInsetsController().setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS);
        }
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.dark_blue_colour));



        classcode = getArguments().getString("class_code");

        class_link = view.findViewById(R.id.class_link);
        class_description = view.findViewById(R.id.class_description);
        select_date = view.findViewById(R.id.select_date);
        select_time = view.findViewById(R.id.select_time);
        schedule_button = view.findViewById(R.id.schedule_button);
        myCalendar = Calendar.getInstance();


        schedule_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DestinationService service = ServiceBuilder.INSTANCE.buildService(DestinationService.class);
                Schedule_Class_Request schedule_class = new Schedule_Class_Request(classcode,select_date.getText().toString(),select_time.getText().toString(),class_description.getText().toString(),class_link.getText().toString());
                Call<Schedule_Class_Response> request = service.schedule_Class(schedule_class);
                request.enqueue(new Callback<Schedule_Class_Response>() {
                    @Override
                    public void onResponse(Call<Schedule_Class_Response> call, Response<Schedule_Class_Response> response) {

                        if(response.body().getMessage().equals("Class scheduled!"));
                        {
                            Toast.makeText(getContext(), "Class Scheduled", Toast.LENGTH_LONG).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                                    new Calender_Fragment(), "Calender_FRAGMENT").commit();


                        }
                    }

                    @Override
                    public void onFailure(Call<Schedule_Class_Response> call, Throwable t) {

                    }
                });

        }
        });


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


        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            DatePickerDialog datePickerDialog =    new DatePickerDialog(getActivity(), R.style.DialogTheme, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();

                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getContext(),R.color.dark_blue_colour));
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(),R.color.dark_blue_colour));

            }
        });

        select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        myCalendar.set(Calendar.HOUR_OF_DAY, i);
                        myCalendar.set(Calendar.MINUTE, i1);

                        String status = "AM";

                        if (i > 11) {

                            status = "PM";
                        }


                        int hour_of_12_hour_format;

                        if (i > 11) {


                            hour_of_12_hour_format = i - 12;
                        } else {
                            hour_of_12_hour_format = i;
                        }
                        select_time.setText(hour_of_12_hour_format + ":" + i1 + " " + status);
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), R.style.DialogTheme, time_listener, myCalendar.HOUR_OF_DAY, myCalendar.MINUTE, false);
                timePickerDialog.show();
                timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.dark_blue_colour));
                timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.dark_blue_colour));

            }
        });


        return view;

    }


    private void updateLabel() {
        DateFormat date = new SimpleDateFormat("MM/dd/yyyy");

        date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String time1 = date.format(myCalendar.getTime());
        select_date.setText(time1);
    }
}