package com.example.elite_classroom.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.elite_classroom.R;

public class Teacher_Instructions_Fragment extends Fragment {


    String title, description, work_id, due_data, attachment_link, class_code,user_status;
    TextView  due_date, title_field,description_field;
    ImageView file_symbol;
    TextView file_name;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teacher_instruction_fragment, container, false);

        title=           getArguments().getString("title");
        description=      getArguments().getString("description");
        work_id=          getArguments().getString("work_id");
        due_data=           getArguments().getString("due_data");
        attachment_link=   getArguments().getString("attachment_link");
        class_code=        getArguments().getString("class_code");



        due_date = view.findViewById(R.id.due_date);
        title_field    = view.findViewById(R.id.title);
        description_field = view.findViewById(R.id.description);
        file_symbol = view.findViewById(R.id.file_symbol);
        file_name= view.findViewById(R.id.file_name);


        title_field.setText(title);
        due_date.setText(due_data);
        description_field.setText(description);

        String mineType="";
        if(!attachment_link.isEmpty())
        {
            file_name.setText(attachment_link.substring(attachment_link.lastIndexOf('/')+1));
            mineType=attachment_link.substring(attachment_link.lastIndexOf('.')) ;
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
