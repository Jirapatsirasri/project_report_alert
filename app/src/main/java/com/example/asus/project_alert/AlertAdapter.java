package com.example.asus.project_alert;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {

    private Context mCtx;
    private List<Alert> alertList;

    public AlertAdapter(Context mCtx, List<Alert> alertList) {
        this.mCtx = mCtx;
        this.alertList = alertList;
    }

    @Override
    public AlertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_alert, parent, false);
        return new AlertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlertViewHolder holder, int position) {
        Alert alert = alertList.get(position);
        holder.detail.setText(alert.detail);
        holder.location.setText("Location: " + alert.location);
        holder.sent_type.setText("Username: " + alert.sent_type);
        holder.topic.setText("Topic: " + alert.topic);
        holder.type_of_alert.setText("Type of Alert: " + alert.type_of_alert);
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }

    class AlertViewHolder extends RecyclerView.ViewHolder {

        TextView detail, location, sent_type, topic, type_of_alert;

        public AlertViewHolder(@NonNull View itemView) {
            super(itemView);

            detail = itemView.findViewById(R.id.detail);
            location = itemView.findViewById(R.id.location);
            sent_type = itemView.findViewById(R.id.sent_type);
            topic = itemView.findViewById(R.id.topic);
            type_of_alert = itemView.findViewById(R.id.type_of_alert);


        }
    }

}

