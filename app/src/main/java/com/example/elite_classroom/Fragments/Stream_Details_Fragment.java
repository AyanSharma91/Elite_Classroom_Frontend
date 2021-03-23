package com.example.elite_classroom.Fragments;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elite_classroom.R;



public class Stream_Details_Fragment extends Fragment {


    String notes_id,class_code,attachment_id,posted_on,title,description,owner_token;


    TextView due_date, title_field,description_field;
    ImageView file_symbol;
    TextView file_name;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.fragment_stream__details_, container, false);


      notes_id=  getArguments().getString("notes_id");
      class_code=  getArguments().getString("class_code");
      attachment_id=  getArguments().getString("attachment_id");
      posted_on=  getArguments().getString("posted_on");
     title=   getArguments().getString("title");
     description=   getArguments().getString("description");
     owner_token=   getArguments().getString("owner_token");


        due_date = view.findViewById(R.id.due_date);
        title_field    = view.findViewById(R.id.title);
        description_field = view.findViewById(R.id.description);
        file_symbol = view.findViewById(R.id.file_symbol);
        file_name= view.findViewById(R.id.file_name);


        due_date.setText(posted_on);
        title_field.setText(title);
        description_field.setText(description);


        String mineType="";
        if(!attachment_id.isEmpty())
        {
            file_name.setText(attachment_id.substring(attachment_id.lastIndexOf('/')+1));
            mineType=attachment_id.substring(attachment_id.lastIndexOf('.')) ;
        }


        switch (mineType)
        {
            case ".pdf":
            {
                file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.pdf_placeholder));
                break;
            }

            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
            {

                file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ms_word_placeholder));
                break;
            }

            case "application/vnd.openxmlformats-officedocument.presentationml.presentation":
            {
                file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.powerpoint_placeholder));
                break;
            }

            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
            {
                file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.exce_placeholder));
                break;
            }
            default:{

                if(mineType.contains("image"))
                {
                    file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.image_placeholder));
                }
                else if(mineType.contains("video"))
                {
                    file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.video_placeholder));
                }
                else
                {
                    file_symbol.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ic_attachment));
                }
            }

        }







        return view;
    }
}