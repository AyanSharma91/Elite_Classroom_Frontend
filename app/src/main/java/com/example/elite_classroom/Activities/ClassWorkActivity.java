package com.example.elite_classroom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.elite_classroom.Fragments.Teacher.NewAssignmentFragment;
import com.example.elite_classroom.Fragments.Teacher.NewMaterialFragment;
import com.example.elite_classroom.PointDialog;
import com.example.elite_classroom.R;

public class ClassWorkActivity extends AppCompatActivity implements PointDialog.PointDialogListener {
ImageView cross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_work);
        Intent i = getIntent();
        int u = i.getIntExtra("u",0);
        if(u==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container2,
                    new NewAssignmentFragment()).commit();
        }
        else if(u==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container2,
                    new NewMaterialFragment()).commit();
        }
        cross = findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClassWorkActivity.this,ClassActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void applyTexts(String points) {

    }
}