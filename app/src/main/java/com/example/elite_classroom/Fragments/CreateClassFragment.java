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

import com.example.elite_classroom.MainActivity;
import com.example.elite_classroom.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CreateClassFragment extends Fragment {
    EditText et,et1,et2,et3;
    String class_name,section,room,subject;
    Button b;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createclass, container, false);
        et = view.findViewById(R.id.class_name);
        et1 = view.findViewById(R.id.section);
        et2 = view.findViewById(R.id.room);
        et3 = view.findViewById(R.id.subject);
        b = view.findViewById(R.id.btn_create);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Class Name is must",Toast.LENGTH_SHORT).show();
                }
                else{
                    class_name = et.getText().toString();
                    section = et1.getText().toString();
                    room = et2.getText().toString();
                    subject = et3.getText().toString();
                }
            }
        });
        return view;
    }
}
