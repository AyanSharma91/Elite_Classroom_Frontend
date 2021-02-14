package com.example.elite_classroom.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.InputQueue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
    String sharedPrefFile = "Login_Credentials";
    public static TextView textView;
    SharedPreferences preferences;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.white));
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                new ClassFragment(), "HOME_FRAGMENT").commit();
        textView = findViewById(R.id.name);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar action = getSupportActionBar();
        action.setDisplayShowTitleEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        drawer = findViewById(R.id.drawer_layout);
        preferences =MainActivity.this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_class:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                        new ClassFragment(),"HOME_FRAGMENT").commit();
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
            {



                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

                mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
                mGoogleSignInClient.signOut();

                SharedPreferences.Editor editor =  preferences.edit();
                editor.putString("name", null);
                editor.putString("email",null);
                editor.putString("jwt_token", null);
                editor.putString("google_token", null);
                editor.apply();
                editor.commit();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                Toast.makeText(MainActivity.this,"Signed_Out",Toast.LENGTH_LONG).show();
                startActivity(intent);



        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account!=null)
        {
            Toast.makeText(MainActivity.this,account.getEmail(),Toast.LENGTH_LONG).show();
        }
                break;
            }


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);}
        else{

            ClassFragment myFragment = (ClassFragment) getSupportFragmentManager().findFragmentByTag("HOME_FRAGMENT");
            if (myFragment != null && myFragment.isVisible()) {
                finishAffinity();
            }
            else
            {
                super.onBackPressed();
            }

        }

    }
}