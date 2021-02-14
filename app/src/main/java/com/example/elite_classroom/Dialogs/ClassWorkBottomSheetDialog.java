package com.example.elite_classroom.Dialogs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.elite_classroom.Activities.ClassWorkActivity;
import com.example.elite_classroom.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ClassWorkBottomSheetDialog extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classworkbottom_sheet, container, false);
        TextView assignment = view.findViewById(R.id.assignment);
        TextView material  = view.findViewById(R.id.material);
        assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ClassWorkActivity.class);
                i.putExtra("u",0);
                startActivity(i);
                dismiss();
            }
        });
        material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ClassWorkActivity.class);
                i.putExtra("u",1);
                startActivity(i);
                dismiss();
            }
        });
        return view;
    }
}
