package com.example.elite_classroom.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.elite_classroom.Fragments.ClassWorkFragment;
import com.example.elite_classroom.Fragments.PeopleFragment;
import com.example.elite_classroom.Fragments.StreamFragment;
import com.example.elite_classroom.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class ClassActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    TextView name_second;

    String sharedPrefFile = "Login_Credentials";
    SharedPreferences preferences;
    TextView settings;
    Bundle bundle;
    public static String classCode, owner_id, class_name, owner_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        preferences =ClassActivity.this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        settings = findViewById(R.id.settings);



        name_second = findViewById(R.id.name_second);
        BottomNavigationView btview = findViewById(R.id.bottom_navigation);


        Intent intent = getIntent();
        classCode = intent.getStringExtra("class_code");
        owner_id = intent.getStringExtra("owner_id");
        class_name = intent.getStringExtra("class_name");
        owner_name = intent.getStringExtra("owner_name");

        bundle = new Bundle();
        bundle.putString("class_code", classCode);
        bundle.putString("class_name", class_name);
        bundle.putString("owner_name", owner_name);
        bundle.putString("owner_id", owner_id);


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassActivity.this, ChatActivity.class);
                intent.putExtra("class_code",classCode);
                intent.putExtra("google_token",preferences.getString("google_token",null));
                startActivity(intent);
            }
        });


        BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_stream:

                                StreamFragment streamFragment = new StreamFragment();
                                streamFragment.setArguments(bundle);

                                name_second.setText("");
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container1,
                                        streamFragment).commit();
                                break;
                            case R.id.nav_classwork:
                                if (class_name.length() > 9) {
                                    name_second.setText(class_name.substring(0, 9) + "...");

                                } else {
                                    name_second.setText(class_name);
                                }

                                ClassWorkFragment classWorkFragment = new ClassWorkFragment();
                                classWorkFragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container1,
                                        classWorkFragment).commit();
                                break;
                            case R.id.nav_people:

                                if (class_name.length() > 9) {
                                    name_second.setText(class_name.substring(0, 9) + "...");

                                } else {
                                    name_second.setText(class_name);
                                }
                                PeopleFragment peopleFragment = new PeopleFragment();
                                peopleFragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container1,
                                        peopleFragment).commit();
                                break;
                        }
                        return true;
                    }
                };

        if (getIntent().getBooleanExtra("from_Classwork", false)) {

            if (class_name.length() > 9) {
                name_second.setText(class_name.substring(0, 9) + "...");

            } else {
                name_second.setText(class_name);
            }
            ClassWorkFragment classWorkFragment = new ClassWorkFragment();
            classWorkFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container1,
                    classWorkFragment).commit();
            btview.setSelectedItemId(R.id.nav_classwork);

        } else {

            StreamFragment streamFragment = new StreamFragment();
            streamFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container1,
                    streamFragment).commit();

        }


        btview.setOnNavigationItemSelectedListener(navListener);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_class:
                Intent i = new Intent(ClassActivity.this, MainActivity.class);
                startActivity(i);
                finish();
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
            case R.id.nav_about:

                break;
            case R.id.nav_feedback:

                break;
            case R.id.nav_signout:

                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}