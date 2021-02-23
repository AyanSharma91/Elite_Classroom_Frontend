package com.example.elite_classroom.Fragments.ToDo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.elite_classroom.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ToDoFragment extends Fragment {

    BottomNavigationView todo_bottom_navigation;
    FrameLayout todo_frame_layout;
    View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        todo_frame_layout = view.findViewById(R.id.todo_frame_layout);

        todo_bottom_navigation = view.findViewById(R.id.todo_bottom_navigation);
        todo_bottom_navigation.setSelectedItemId(R.id.nav_submitted);
        getFragmentManager().beginTransaction().replace(R.id.todo_frame_layout, new SubmittedFragment()).commit();

        todo_bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_submitted:
                        getFragmentManager().beginTransaction().replace(R.id.todo_frame_layout,
                                new SubmittedFragment()).commit();
                        return true;
                    case R.id.nav_pending:
                        getFragmentManager().beginTransaction().replace(R.id.todo_frame_layout,
                                new PendingFragment()).commit();
                        return true;
                    case R.id.nav_missed:
                        getFragmentManager().beginTransaction().replace(R.id.todo_frame_layout,
                                new MissedFragment()).commit();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_to_do, container, false);
        return view;
    }
}