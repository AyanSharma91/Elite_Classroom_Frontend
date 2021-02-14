package com.example.elite_classroom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elite_classroom.ModalClass.ClassWork;
import com.example.elite_classroom.ModalClass.Stream;
import com.example.elite_classroom.R;

import java.util.List;

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.ViewHolder> {
    List<Stream> list;
    Context ctx;

    public StreamAdapter(List<Stream> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public StreamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_classwork,parent,false);
        return new StreamAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StreamAdapter.ViewHolder holder, int position) {
        Stream l = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView t,t1;
        View mview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.classwork_image);
            t = itemView.findViewById(R.id.classwork_title);
            t1 = itemView.findViewById(R.id.classwork_description);
            mview = itemView;
        }
    }
}
