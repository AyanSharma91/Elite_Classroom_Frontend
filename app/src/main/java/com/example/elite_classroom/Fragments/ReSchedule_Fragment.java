package com.example.elite_classroom.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
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

import com.example.elite_classroom.Models.Retrofit_Models.Auth_Responses;
import com.example.elite_classroom.Models.Retrofit_Models.Cancel_Class_Request;
import com.example.elite_classroom.Models.Retrofit_Models.Google_Logins;
import com.example.elite_classroom.Models.Retrofit_Models.Reschedule_Class;
import com.example.elite_classroom.Models.Retrofit_Models.Reschedule_Response;
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


public class ReSchedule_Fragment extends Fragment {


   String class_links="",class_descriptions="",type="",old_time="",class_code="";

   TextView select_date , select_time;
   ImageView schedule_button,reschedule_button;
    Calendar myCalendar;
    ImageView  delete_class,cross;
   AppCompatEditText class_link,class_description;
   Window window;
   TextView due_date_text;
   TextView due;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view =  inflater.inflate(R.layout.fragment_re_schedule_, container, false);



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


        due_date_text = view.findViewById(R.id.due_date_text);
        due = view.findViewById(R.id.due);
        cross = view.findViewById(R.id.cross);
      cross.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                      new Calender_Fragment(),"Calender_FRAGMENT").commit();

          }
      });

       select_date= view.findViewById(R.id.select_date);
       reschedule_button = view.findViewById(R.id.reschedule_button);
       select_time = view.findViewById(R.id.select_time);
        class_link = view.findViewById(R.id.class_link);
        class_description = view.findViewById(R.id.class_description);

       class_links = getArguments().getString("class_link");
       class_descriptions= getArguments().getString("class_description");
       type = getArguments().getString("is_owner");
       old_time = getArguments().getString("old_time");
        class_code = getArguments().getString("class_code");
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



        select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        myCalendar.set(Calendar.HOUR_OF_DAY,i);
                        myCalendar.set(Calendar.MINUTE, i1);

                        String status = "AM";

                        if(i > 11)
                        {

                            status = "PM";
                        }


                        int hour_of_12_hour_format;

                        if(i > 11){


                            hour_of_12_hour_format = i - 12;
                        }
                        else {
                            hour_of_12_hour_format = i;
                        }
                        select_time.setText(hour_of_12_hour_format + ":" + i1 + " " + status);
                    }
                };
                new TimePickerDialog(getActivity(),time_listener, myCalendar.HOUR_OF_DAY, myCalendar.MINUTE,false).show();
            }
        });

        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

       if(type.equals("participant"))
       {

           select_date.setVisibility(View.GONE);
           select_time.setVisibility(View.GONE);
           reschedule_button.setVisibility(View.GONE);
           due_date_text.setVisibility(View.GONE);
           due.setVisibility(View.GONE);


       }
       else
       {
           reschedule_button.setVisibility(View.VISIBLE);


       }

//       class_link.setText(Html.fromHtml(class_links));
        class_link.setText(class_links);

        class_description.setText(class_descriptions);
        class_link.setMovementMethod(LinkMovementMethod.getInstance());



        reschedule_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DestinationService service = ServiceBuilder.INSTANCE.buildService(DestinationService.class);
                Reschedule_Class reschedule_class = new Reschedule_Class(class_code,select_date.getText().toString(),old_time,select_time.getText().toString(),class_descriptions,class_links);
                 Log.d("Credentials", class_code+"      "+select_date.getText().toString()+"          "+old_time +"            "+select_time.getText().toString()+"                "+class_descriptions +"                       "+class_links);
                Call<Reschedule_Response> request = service.reschedule_Class(reschedule_class);
                request.enqueue(new Callback<Reschedule_Response>() {
                    @Override
                    public void onResponse(Call<Reschedule_Response> call, Response<Reschedule_Response> response) {

                        if(response.body().getMessage().equals("Class Rescheduled!"))
                        {
                            Toast.makeText(getContext(),"Class Rescheduled",Toast.LENGTH_LONG).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                                    new Calender_Fragment(), "Calender_FRAGMENT").commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<Reschedule_Response> call, Throwable t) {

                        Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


        return  view;
    }

    private void updateLabel() {
        DateFormat date = new SimpleDateFormat("MM/dd/yyyy");

        date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String time1 = date.format(myCalendar.getTime());
        select_date.setText(time1);
    }
}