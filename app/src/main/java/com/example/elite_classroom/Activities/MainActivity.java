package com.example.elite_classroom.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elite_classroom.Fragments.ClassFragment;
import com.example.elite_classroom.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.security.AlgorithmConstraints;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    Button sign_out_button ;
    GoogleSignInClient mGoogleSignInClient;
    public static TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                new ClassFragment()).commit();
        textView = findViewById(R.id.name);
//        sign_out_button= findViewById(R.id.sign_out_button);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//        sign_out_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                        .requestEmail()
//                        .build();
//
//                mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
//                mGoogleSignInClient.signOut();
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                Toast.makeText(MainActivity.this,"Signed_Out",Toast.LENGTH_LONG).show();
//                startActivity(intent);
//            }
//        });
//
//
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//
//        if(account!=null)
//        {
//            Toast.makeText(MainActivity.this,account.getEmail(),Toast.LENGTH_LONG).show();
//
//        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_class:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new ClassFragment()).commit();
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
        return true;}
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