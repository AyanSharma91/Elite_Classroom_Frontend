package com.example.elite_classroom.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.elite_classroom.Adapter.ViewPagerAdapter;
import com.example.elite_classroom.R;
import com.google.android.material.tabs.TabLayout;

public class Teacher_Assignment_Fragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewpager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teacher_assignment_fragment, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_id);
        tabLayout.addTab(tabLayout.newTab().setText("Instructions"));
        tabLayout.addTab(tabLayout.newTab().setText("Student Work"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewpager =(ViewPager)view.findViewById(R.id.viewpager_id);
        ViewPagerAdapter tabsAdapter = new ViewPagerAdapter(getFragmentManager(),tabLayout.getTabCount());
        viewpager.setAdapter(tabsAdapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager.getAdapter().notifyDataSetChanged();
        return view;
    }
}
