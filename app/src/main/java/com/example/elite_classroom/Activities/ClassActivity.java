package com.example.elite_classroom.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.elite_classroom.Fragments.ClassFragment;
import com.example.elite_classroom.Fragments.StreamFragment;
import com.example.elite_classroom.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class ClassActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container1,
                new StreamFragment()).commit();
        BottomNavigationView btview = findViewById(R.id.bottom_navigation);
        btview.setOnNavigationItemSelectedListener(navListener);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_stream:

                            break;
                        case R.id.nav_classwork:

                            break;
                        case R.id.nav_people:

                            break;
                    }
                    return true;
                }
    };
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_class:
                Intent i = new Intent(ClassActivity.this,MainActivity.class);
                startActivity(i);
                break;
            case R.id.nav_calender:

                break;
            case R.id.nav_todo:

                break;
            case R.id.nav_archived:

                break;
            case R.id.nav_folder:

                break;
            case R.id.nav_setting:

                break;
            case R.id.nav_help:

                break;
            case R.id.nav_signout:

                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);}
        else{
            super.onBackPressed();
        }
//        else if(i == 0){
//            Intent a = new Intent(Intent.ACTION_MAIN);
//            a.addCategory(Intent.CATEGORY_HOME);
//            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(a);}
//        else if(i==1){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new SellerFragment()).commit();
//        }
//        else if(i==2){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new ItemListFragment()).commit();
//        }
//        else if(i==3){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new OrderFragment()).commit();
//        }
//        else if(i==4){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new OrderFragment()).commit();
//        }
    }
}