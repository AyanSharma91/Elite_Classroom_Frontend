package com.example.elite_classroom.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elite_classroom.Activities.Assignment_Submission_Activity;
import com.example.elite_classroom.Activities.LoginActivity;
import com.example.elite_classroom.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubmittedAdapter extends RecyclerView.Adapter<SubmittedAdapter.submittedViewHolder>
{
    private Context context;
    private JSONArray data;

    String sharedPrefFile = "Login_Credentials";
    public static SharedPreferences preferences;
    public SubmittedAdapter(Context context, JSONArray data)
    {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public submittedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rvsubmitted_item, parent, false);
        return new submittedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull submittedViewHolder holder, int position) {


        preferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject_second = null;
                try {
                    jsonObject_second = (JSONObject) data.get(position);
                    Intent intent = new Intent(context, Assignment_Submission_Activity.class);
                    intent.putExtra("title", jsonObject_second.optString("title"));
                    intent.putExtra("description",jsonObject_second.optString("description"));
                    intent.putExtra("work_id", jsonObject_second.optString("work_id"));
                    intent.putExtra("due_data",jsonObject_second.optString("due_date"));
                    intent.putExtra("attachment_link", jsonObject_second.optString("attachment"));
                    intent.putExtra("class_code", jsonObject_second.optString("class_code"));

                    intent.putExtra("user_status", "assignment_student");
                    context.startActivity(intent);

//                    if(( jsonObject_second.optString("class_code").equals(preferences.getString("google_token",null))))
//                    {
//
//                        intent.putExtra("user_status", "assignment_owner");
//
//                    }
//                    else
//                    {
//                        intent.putExtra("user_status", "assignment_student");
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) data.get(position);

            holder.classwork_title.setText(jsonObject.optString("title"));
            holder.class_name.setText(jsonObject.optString("class_name"));
            holder.date_created.setText(getDateFormat(jsonObject.optString("created_date")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    public class submittedViewHolder extends RecyclerView.ViewHolder{

        TextView classwork_title,class_name,date_created;
        RelativeLayout parent_layout;

        public submittedViewHolder(@NonNull View itemView) {
            super(itemView);

            classwork_title = itemView.findViewById(R.id.classwork_title);
            class_name = itemView.findViewById(R.id.class_name);
            date_created = itemView.findViewById(R.id.date_created);
            parent_layout = itemView.findViewById(R.id.parent_layout);


        }
    }

    String getDateFormat(String date)
    {
        String identified_date;

        identified_date="Posted ";

        switch(date.substring(5,7))
        {
            case "01":
            {
                identified_date=identified_date+"Jan";
                break;
            }

            case "02":
            {
                identified_date=identified_date+"Feb";
                break;
            }
            case "03":
            {
                identified_date=identified_date+"Mar";
                break;
            }
            case "04":
            {
                identified_date=identified_date+"Apr";
                break;
            }
            case "05":
            {
                identified_date=identified_date+"May";
                break;
            }
            case "06":
            {
                identified_date=identified_date+"Jun";
                break;
            }
            case "07":
            {
                identified_date=identified_date+"July";
                break;
            }
            case "08":
            {
                identified_date=identified_date+"Aug";
                break;
            }
            case "09":
            {
                identified_date=identified_date+"Sep";
                break;
            }
            case "10":
            {
                identified_date=identified_date+"Oct";
                break;
            }
            case "11":
            {
                identified_date=identified_date+"Nov";
                break;
            }
            case "12":
            {
                identified_date=identified_date+"Dec";
                break;
            }


        }

        identified_date=identified_date+" "+date.substring(8,10);

        return identified_date;


    }
}
