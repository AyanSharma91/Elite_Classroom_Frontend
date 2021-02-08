package com.example.elite_classroom.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.elite_classroom.ClassBottomSheetDialog;
import com.example.elite_classroom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ClassFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        FloatingActionButton buttonAddNote = view.findViewById(R.id.class_bottom);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassBottomSheetDialog bottomSheet = new ClassBottomSheetDialog();
                bottomSheet.show(getFragmentManager(), "ClassBottomSheet");
            }
        });
        return view;
    }
}
