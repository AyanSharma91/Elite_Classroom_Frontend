package com.example.elite_classroom.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elite_classroom.Models.Recycler_Models.Class_Fixtures;
import com.example.elite_classroom.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Calender_Adapter extends RecyclerView.Adapter<Calender_Adapter.ViewHolder> {

    List<Class_Fixtures> list1;
    Context context;
    Boolean is_current;

    public Calender_Adapter(Context context, ArrayList<Class_Fixtures> list,Boolean is_current) {
        this.list1=list;
        this.context=context;
        this.is_current = is_current;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.single_row_calender_recycler_view, viewGroup, false);
        return new Calender_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if(is_current)
        {
            viewHolder.class_name.setText(list1.get(i).getClass_name());

            if(!(list1.get(i).getMon().isEmpty()))
            {

                if((list1.get(i).getMon().contains("first")))
                {
                    viewHolder.week_day.setText("MON");
                    viewHolder.class_time.setText(list1.get(i).getMon().substring(0,8));
                    viewHolder.date.setText(getDate(2).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getMon());
                    viewHolder.date.setText("");
                }


            }
            else if(!(list1.get(i).getTue().isEmpty()))
            {
                if((list1.get(i).getTue().contains("first")))
                {
                    viewHolder.week_day.setText("TUE");
                    viewHolder.class_time.setText(list1.get(i).getTue().substring(0,8));
                    viewHolder.date.setText(getDate(3).toString());
                }
                else {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getTue());
                    viewHolder.date.setText("");

                }


            }
            else if(!(list1.get(i).getWed().isEmpty()))
            {
                if((list1.get(i).getWed().contains("first")))
                {
                    viewHolder.week_day.setText("WED");
                    viewHolder.class_time.setText(list1.get(i).getWed().substring(0,8));
                    viewHolder.date.setText(getDate(4).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getWed());
                    viewHolder.date.setText("");
                }

            }
            else if(!(list1.get(i).getThu().isEmpty()))
            {
                if((list1.get(i).getThu().contains("first")))
                {
                    viewHolder.week_day.setText("THU");
                    viewHolder.class_time.setText(list1.get(i).getThu().substring(0,8));
                    viewHolder.date.setText(getDate(5).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getThu());
                    viewHolder.date.setText("");
                }

            }
            else  if(!(list1.get(i).getFri().isEmpty()))
            {
                if((list1.get(i).getFri().contains("first")))
                {
                    viewHolder.week_day.setText("FRI");
                    viewHolder.class_time.setText(list1.get(i).getFri().substring(0,8));
                    viewHolder.date.setText(getDate(6).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getFri());
                    viewHolder.date.setText("");
                }

            }
            else  if(!(list1.get(i).getSat().isEmpty())) {

                if ((list1.get(i).getSat().contains("first"))) {
                    viewHolder.week_day.setText("SAT");
                    viewHolder.class_time.setText(list1.get(i).getSat().substring(0, 8));
                    viewHolder.date.setText(getDate(7).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getSat());
                    viewHolder.date.setText("");
                }


            }
            else   if(!(list1.get(i).getSun().isEmpty()))
            {
                if((list1.get(i).getSun().contains("first")))
                {
                    viewHolder.week_day.setText("SUN");
                    viewHolder.class_time.setText(list1.get(i).getSun().substring(0,8));
                    viewHolder.date.setText(getDate(1+7).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getSun());
                    viewHolder.date.setText("");
                }

            }

        }
        else
        {

            viewHolder.class_name.setText(list1.get(i).getClass_name());

            if(!(list1.get(i).getMon().isEmpty()))
            {

                if((list1.get(i).getMon().contains("first")))
                {
                    viewHolder.week_day.setText("MON");
                    viewHolder.class_time.setText(list1.get(i).getMon().substring(0,8));
                    viewHolder.date.setText(getDate_next_week(2).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getMon());
                    viewHolder.date.setText("");
                }


            }
            else if(!(list1.get(i).getTue().isEmpty()))
            {
                if((list1.get(i).getTue().contains("first")))
                {
                    viewHolder.week_day.setText("TUE");
                    viewHolder.class_time.setText(list1.get(i).getTue().substring(0,8));
                    viewHolder.date.setText(getDate_next_week(3).toString());
                }
                else {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getTue());
                    viewHolder.date.setText("");

                }


            }
            else if(!(list1.get(i).getWed().isEmpty()))
            {
                if((list1.get(i).getWed().contains("first")))
                {
                    viewHolder.week_day.setText("WED");
                    viewHolder.class_time.setText(list1.get(i).getWed().substring(0,8));
                    viewHolder.date.setText(getDate_next_week(4).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getWed());
                    viewHolder.date.setText("");
                }

            }
            else if(!(list1.get(i).getThu().isEmpty()))
            {
                if((list1.get(i).getThu().contains("first")))
                {
                    viewHolder.week_day.setText("THU");
                    viewHolder.class_time.setText(list1.get(i).getThu().substring(0,8));
                    viewHolder.date.setText(getDate_next_week(5).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getThu());
                    viewHolder.date.setText("");
                }

            }
            else  if(!(list1.get(i).getFri().isEmpty()))
            {
                if((list1.get(i).getFri().contains("first")))
                {
                    viewHolder.week_day.setText("FRI");
                    viewHolder.class_time.setText(list1.get(i).getFri().substring(0,8));
                    viewHolder.date.setText(getDate_next_week(6).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getFri());
                    viewHolder.date.setText("");
                }

            }
            else  if(!(list1.get(i).getSat().isEmpty())) {

                if ((list1.get(i).getSat().contains("first"))) {
                    viewHolder.week_day.setText("SAT");
                    viewHolder.class_time.setText(list1.get(i).getSat().substring(0, 8));
                    viewHolder.date.setText(getDate_next_week(7).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getSat());
                    viewHolder.date.setText("");
                }


            }
            else   if(!(list1.get(i).getSun().isEmpty()))
            {
                if((list1.get(i).getSun().contains("first")))
                {
                    viewHolder.week_day.setText("SUN");
                    viewHolder.class_time.setText(list1.get(i).getSun().substring(0,8));
                    viewHolder.date.setText(getDate_next_week(1+7).toString());
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getSun());
                    viewHolder.date.setText("");
                }

            }


        }



    }


    @Override
    public int getItemCount() {
        return list1.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  week_day ;
        TextView date;
        TextView class_name;
        TextView class_time ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            week_day = itemView.findViewById(R.id.week_day);
            date= itemView.findViewById(R.id.date) ;
            class_name = itemView.findViewById(R.id.class_name);
            class_time = itemView.findViewById(R.id.class_time);
        }
    }

    Integer getDate(Integer day)
    {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        Integer current_date = Integer.parseInt(formattedDate.substring(0,2));
        Integer current_month = Integer.parseInt(formattedDate.substring(3,5));
        Integer  current_day = Calendar.DAY_OF_MONTH+2;


        Integer current_year = Integer.parseInt(formattedDate.substring(6));

        Log.d("Current_Data", current_date+" "+current_month+" "+current_day+" "+current_year);
        if(current_day>day)
        {
            current_date -= (current_day-day);
            if(current_month==1 || current_month==3 || current_month==5 || current_month==7 || current_month==8 || current_month==10 || current_month==12)
            {
               if(current_date>31)
               {
                   current_date = current_date-31;
                   return current_date;
               }
            }
           else if(current_month==4 ||current_month==6 ||current_month==9 ||current_month==11 )
            {
                 if(current_date>30)
                 {
                     current_date =current_date-30;
                     return current_date;
                 }
            }
            else if(current_month==2)
                {

                        if(current_year%400==0 || current_year%4==0)
                        {
                            //Leap year
                            if(current_date>29)
                            {
                                current_date = current_date-29;
                                return current_date;
                            }
                        }
                        else if(current_year%100==0)
                        {
                            //Not a Leap year
                            if(current_date>28)
                            {
                                current_date = current_date-28;
                                return current_date;
                            }
                        }
                        else
                        {
                            //Not aLeap year
                            //Not a Leap year
                            if(current_date>28)
                            {
                                current_date = current_date-28;
                                return current_date;
                            }
                        }
                }



        }
        else if(current_day<day)
        {
            current_date += (day-current_day);
            if(current_month==1 || current_month==3 || current_month==5 || current_month==7 || current_month==8 || current_month==10 || current_month==12)
            {
                if(current_date>31)
                {
                    current_date = current_date-31;
                    return current_date;
                }
            }
            else if(current_month==4 ||current_month==6 ||current_month==9 ||current_month==11 )
            {
                if(current_date>30)
                {
                    current_date =current_date-30;
                    return current_date;
                }
            }
            else
            if(current_month==2)
            {

                if(current_year%400==0 || current_year%4==0)
                {
                    //Leap year
                    if(current_date>29)
                    {
                        current_date = current_date-29;
                        return current_date;
                    }
                }
                else if(current_year%100==0)
                {
                    //Not a Leap year
                    if(current_date>28)
                    {
                        current_date = current_date-28;
                        return current_date;
                    }
                }
                else
                {
                    //Not aLeap year
                    //Not a Leap year
                    if(current_date>28)
                    {
                        current_date = current_date-28;
                        return current_date;
                    }
                }
            }
        }


        return current_date;

    }

    Integer getDate_next_week(Integer day)
    {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        Integer current_date = Integer.parseInt(formattedDate.substring(0,2))+7;
        Integer current_month = Integer.parseInt(formattedDate.substring(3,5));
        Integer  current_day = Calendar.DAY_OF_MONTH+2;


        Integer current_year = Integer.parseInt(formattedDate.substring(6));

        Log.d("Current_Data", current_date+" "+current_month+" "+current_day+" "+current_year);
        if(current_day>day)
        {
            current_date -= (current_day-day);
            if(current_month==1 || current_month==3 || current_month==5 || current_month==7 || current_month==8 || current_month==10 || current_month==12)
            {
                if(current_date>31)
                {
                    current_date = current_date-31;
                    return current_date;
                }
            }
            else if(current_month==4 ||current_month==6 ||current_month==9 ||current_month==11 )
            {
                if(current_date>30)
                {
                    current_date =current_date-30;
                    return current_date;
                }
            }
            else if(current_month==2)
            {

                if(current_year%400==0 || current_year%4==0)
                {
                    //Leap year
                    if(current_date>29)
                    {
                        current_date = current_date-29;
                        return current_date;
                    }
                }
                else if(current_year%100==0)
                {
                    //Not a Leap year
                    if(current_date>28)
                    {
                        current_date = current_date-28;
                        return current_date;
                    }
                }
                else
                {
                    //Not aLeap year
                    //Not a Leap year
                    if(current_date>28)
                    {
                        current_date = current_date-28;
                        return current_date;
                    }
                }
            }



        }
        else if(current_day<day)
        {
            current_date += (day-current_day);
            if(current_month==1 || current_month==3 || current_month==5 || current_month==7 || current_month==8 || current_month==10 || current_month==12)
            {
                if(current_date>31)
                {
                    current_date = current_date-31;
                    return current_date;
                }
            }
            else if(current_month==4 ||current_month==6 ||current_month==9 ||current_month==11 )
            {
                if(current_date>30)
                {
                    current_date =current_date-30;
                    return current_date;
                }
            }
            else
            if(current_month==2)
            {

                if(current_year%400==0 || current_year%4==0)
                {
                    //Leap year
                    if(current_date>29)
                    {
                        current_date = current_date-29;
                        return current_date;
                    }
                }
                else if(current_year%100==0)
                {
                    //Not a Leap year
                    if(current_date>28)
                    {
                        current_date = current_date-28;
                        return current_date;
                    }
                }
                else
                {
                    //Not aLeap year
                    //Not a Leap year
                    if(current_date>28)
                    {
                        current_date = current_date-28;
                        return current_date;
                    }
                }
            }
        }


        return current_date;

    }
}
