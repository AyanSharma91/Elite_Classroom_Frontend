package com.example.elite_classroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PendingMissedAdapter extends RecyclerView.Adapter<PendingMissedAdapter.pendingMissedViewHolder>
{
    Context context;
    PendingMissedModel data;

    public PendingMissedAdapter(Context context, PendingMissedModel data)
    {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public pendingMissedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rvpending_missed_item, parent, false);
        return new pendingMissedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pendingMissedViewHolder holder, int position) {
        holder.tvTitle.setText(data.getData().get(position).getTitle());
        holder.tvDescription.setText(data.getData().get(position).getDescription());
        holder.tvClassName.setText(data.getData().get(position).getClassName());
        holder.tvProdID.setText(data.getData().get(position).getProfId());
        holder.tvCreationDate.setText(data.getData().get(position).getCreatedDate().substring(0,10));
        holder.tvDueDate.setText(data.getData().get(position).getDueDate().substring(0,10));
    }

    @Override
    public int getItemCount() {
        return data.getData().size();
    }

    public class pendingMissedViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvDescription, tvClassName, tvProdID, tvCreationDate, tvDueDate;

        public pendingMissedViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvClassName = itemView.findViewById(R.id.tvClassName);
            tvProdID = itemView.findViewById(R.id.tvProfID);
            tvCreationDate = itemView.findViewById(R.id.tvCreationDate);
            tvDueDate = itemView.findViewById(R.id.tvDueDate);
        }
    }
}
