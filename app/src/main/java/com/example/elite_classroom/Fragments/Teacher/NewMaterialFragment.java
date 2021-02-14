package com.example.elite_classroom.Fragments.Teacher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.elite_classroom.R;

public class NewMaterialFragment extends Fragment {
    EditText material_title,material_description;
    Button create_material;
    String title,description="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newmaterial, container, false);
        material_title = view.findViewById(R.id.material_title);
        material_description = view.findViewById(R.id.material_description);
        create_material = view.findViewById(R.id.create_material);
        create_material.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       title = material_title.getText().toString();
                       description = material_description.getText().toString();
                       if(title.isEmpty()){
                           material_title.setError("Please enter Title");
                           material_title.requestFocus();
                       }else{
                           createMaterial(title,description);
                       }
                    }
                });
        return view;
    }
    public void createMaterial(String title,String description){

    }
}
