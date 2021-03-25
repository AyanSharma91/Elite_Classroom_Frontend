    package com.example.elite_classroom.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.elite_classroom.Fragments.Teacher.NewAnnouncementFragment;
import com.example.elite_classroom.Fragments.Teacher.NewAssignmentFragment;
import com.example.elite_classroom.Fragments.Teacher.NewMaterialFragment;
import com.example.elite_classroom.Dialogs.PointDialog;
import com.example.elite_classroom.R;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ClassWorkActivity extends AppCompatActivity implements PointDialog.PointDialogListener {

    ImageView cross;
    String class_code="", owner_code,class_name,owner_name;


    public static ImageView attachment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_work);

        attachment =findViewById(R.id.attachment);

        Intent i = getIntent();
        int u = i.getIntExtra("u",0);




        class_code= i.getStringExtra("class_code");
        owner_code= i.getStringExtra("owner_id");
        class_name = i.getStringExtra("class_name");
        owner_name = i.getStringExtra("owner_name");



        Bundle b = new Bundle();
        b.putString("class_code",class_code);
        b.putString("owner_id",owner_code);
        b.putString("class_name",class_name);
        b.putString("owner_name",owner_name);







        if(u==0){

            NewAssignmentFragment newAssignmentFragment = new NewAssignmentFragment();
            newAssignmentFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container2,
                    newAssignmentFragment).commit();
        }
        else if(u==1){
            NewMaterialFragment newMaterialFragment = new NewMaterialFragment();
            newMaterialFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container2,
                    newMaterialFragment).commit();
        }
        else if(u==2){
            NewAnnouncementFragment newAnnouncementFragment = new NewAnnouncementFragment();
            newAnnouncementFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container2,
                   newAnnouncementFragment ).commit();
        }


        cross = findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClassWorkActivity.this,ClassActivity.class);
                i.putExtra("class_code",class_code);
                i.putExtra("owner_id",owner_code);
                i.putExtra("class_name",class_name);
                i.putExtra("owner_name",owner_name);
                i.putExtra("from_Classwork",true);
                startActivity(i);
                finish();
            }
        });
        attachment = findViewById(R.id.attachment);
        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @Override
    public void applyTexts(String points) {

    }



    @Override
    public void onBackPressed() {
        Intent i = new Intent(ClassWorkActivity.this,ClassActivity.class);
        i.putExtra("class_code",class_code);
        i.putExtra("owner_id",owner_code);
        i.putExtra("class_name",class_name);
        i.putExtra("owner_name",owner_name);
        i.putExtra("from_Classwork",true);
        startActivity(i);
        finish();
        super.onBackPressed();
    }


}