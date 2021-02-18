package com.example.elite_classroom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elite_classroom.Models.Recycler_Models.ClassWork;
import com.example.elite_classroom.R;

import java.util.List;

public class ClassWorkAdapter extends RecyclerView.Adapter<ClassWorkAdapter.ViewHolder>  {
    List<ClassWork> list;
    Context ctx;

    public ClassWorkAdapter(List<ClassWork> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ClassWorkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_classwork,parent,false);
        return new ClassWorkAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassWorkAdapter.ViewHolder holder, int position) {
        ClassWork c = list.get(position);
        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(ctx, view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // TODO Auto-generated method stub
                        switch (item.getItemId()) {
                            case R.id.pop_edit:

                                return true;
                            case R.id.pop_delete:

                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.inflate(R.menu.pop_menu);
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView t,t1;
        Button b;
        View mview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.classwork_image);
            t = itemView.findViewById(R.id.classwork_title);
            t1 = itemView.findViewById(R.id.classwork_description);
            b = itemView.findViewById(R.id.pop_button);
            mview = itemView;
        }
    }
}
