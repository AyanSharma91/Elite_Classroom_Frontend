package com.example.elite_classroom.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.elite_classroom.Activities.MainActivity;
import com.example.elite_classroom.Adapter.Get_Classes_Adapter;
import com.example.elite_classroom.Dialogs.ClassBottomSheetDialog;
import com.example.elite_classroom.Models.Recycler_Models.Get_Classes_List;
import com.example.elite_classroom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ClassFragment extends Fragment {
    @Nullable


    FloatingActionButton buttonAddNote;
    RecyclerView recycler_view;
    ArrayList<Get_Classes_List> classes = new ArrayList();
    Get_Classes_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);

        buttonAddNote = view.findViewById(R.id.class_bottom);
        recycler_view  = view.findViewById(R.id.recycler_view);


       for(int i=0;i<5;i++)
       {
           classes.add(new Get_Classes_List("Maths","Dr. Subhash", "2 students"));
       }

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        adapter = new Get_Classes_Adapter(getActivity(),classes);
        recycler_view.setLayoutManager(manager);
        recycler_view.setAdapter(adapter);





        MainActivity.textView.setText("Elite Classroom");

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
