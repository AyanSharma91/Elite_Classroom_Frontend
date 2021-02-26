package com.example.elite_classroom.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elite_classroom.Activities.ClassActivity;
import com.example.elite_classroom.Activities.ClassWorkActivity;
import com.example.elite_classroom.Models.Recycler_Models.ClassWork;
import com.example.elite_classroom.R;

import org.json.JSONObject;

import java.util.List;

public class ClassWorkAdapter extends RecyclerView.Adapter<ClassWorkAdapter.ViewHolder>  {
    List<ClassWork> list;
    Context ctx;
    String token;
    public ClassWorkAdapter(List<ClassWork> list, Context ctx,String token) {
        this.list = list;
        this.ctx = ctx;
        this.token=token;
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
        holder.t.setText(c.getTitle());
        holder.t1.setText(c.getCreated_date());
        if(c.getType()==0)
        holder.iv.setImageDrawable(Drawable.createFromPath("ic_assignment"));
        else
            holder.iv.setImageDrawable(Drawable.createFromPath("ic_announcement"));
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        if(token.equals(c.getOwner_token())){
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
                                String url = "https://elite-classroom-server.herokuapp.com/api/classworks/deleteClasswork/"+ c.getWork_id();
                                RequestQueue requestQueue = Volley.newRequestQueue(ctx);
                                JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        list.remove(position);
                                        notifyItemRemoved(position);
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                requestQueue.add(request);
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.inflate(R.menu.pop_menu);
                popupMenu.show();
            }
        });}else{
            holder.b.setAlpha(0);
        }
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
