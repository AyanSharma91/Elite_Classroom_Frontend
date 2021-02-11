package com.example.elite_classroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubmittedAdapter extends RecyclerView.Adapter<SubmittedAdapter.submittedViewHolder>
{
    private Context context;
    private SubmittedModel data;

    public SubmittedAdapter(Context context, SubmittedModel data)
    {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public submittedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rvsubmitted_item, parent, false);
        return new submittedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull submittedViewHolder holder, int position) {
        Datum assignment = data.getData().get(position);

        holder.tvWork.setText(assignment.getWork());
        holder.tvAttachment.setText(assignment.getAttachment());
        holder.tvSubmissionDate.setText(assignment.getSubmittedOn().substring(0,10));
    }

    @Override
    public int getItemCount() {
        return data.getData().size();
    }

    public class submittedViewHolder extends RecyclerView.ViewHolder{

        TextView tvWork, tvAttachment, tvSubmissionDate;

        public submittedViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWork = itemView.findViewById(R.id.tvWork);
            tvAttachment = itemView.findViewById(R.id.tvAttachment);
            tvSubmissionDate = itemView.findViewById(R.id.tvSubmissionDate);
        }
    }
}
