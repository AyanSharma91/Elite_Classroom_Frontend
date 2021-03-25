package com.example.elite_classroom.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elite_classroom.Activities.ClassActivity;
import com.example.elite_classroom.Models.Recycler_Models.Class_Fixtures;
import com.example.elite_classroom.Models.Recycler_Models.Get_Classes_List;
import com.example.elite_classroom.Models.Retrofit_Models.Get_Calender_Fixtures;
import com.example.elite_classroom.R;

import java.util.ArrayList;
import java.util.List;

public class Calender_Adapter extends RecyclerView.Adapter<Calender_Adapter.ViewHolder> {

    List<Class_Fixtures> list1;
    Context context;

    public Calender_Adapter(Context context, ArrayList<Class_Fixtures> list) {
        this.list1=list;
        this.context=context;
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

        viewHolder.class_name.setText(list1.get(i).getClass_code());

            if(!(list1.get(i).getMon().isEmpty()))
            {

                if((list1.get(i).getMon().contains("first")))
                {
                    viewHolder.week_day.setText("MON");
                    viewHolder.class_time.setText(list1.get(i).getMon().substring(0,8));
                    viewHolder.date.setText("22");
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
                    viewHolder.date.setText("22");
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
                    viewHolder.date.setText("22");
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
                    viewHolder.date.setText("22");
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
                    viewHolder.date.setText("22");
                }
                else
                {
                    viewHolder.week_day.setText("");
                    viewHolder.class_time.setText(list1.get(i).getFri());
                    viewHolder.date.setText("");
                }

            }
                else  if(!(list1.get(i).getSat().isEmpty()))
            {

                if((list1.get(i).getSat().contains("first")))
                {
                    viewHolder.week_day.setText("SAT");
                    viewHolder.class_time.setText(list1.get(i).getSat().substring(0,8));
                    viewHolder.date.setText("22");
                }
                viewHolder.week_day.setText("");
                viewHolder.class_time.setText(list1.get(i).getSat());
                viewHolder.date.setText("");

            }
                else   if(!(list1.get(i).getSun().isEmpty()))
            {
                if((list1.get(i).getSun().contains("first")))
                {
                    viewHolder.week_day.setText("SUN");
                    viewHolder.class_time.setText(list1.get(i).getSun().substring(0,8));
                    viewHolder.date.setText("22");
                }
                else
                {

                }
                viewHolder.week_day.setText("");
                viewHolder.class_time.setText(list1.get(i).getSun());
                viewHolder.date.setText("");
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
}
