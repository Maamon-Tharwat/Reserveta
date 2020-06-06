package com.example.reserveta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reserveta.R;
import com.example.reserveta.model.ReservationModel;
import com.example.reserveta.utils.AppBrain;

import java.util.ArrayList;
import java.util.Date;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private ArrayList<ReservationModel> list;
    private Context context;

    public ReservationAdapter(ArrayList<ReservationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.paitentName.setText(list.get(position).getPatientName());
        holder.reservationTime.setText(AppBrain.getCurrentDate(context,new Date(list.get(position).getReservationTime())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView paitentName;
        TextView reservationTime;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            paitentName=itemView.findViewById(R.id.reservation_item_patient_name);
            reservationTime=itemView.findViewById(R.id.reservation_item_time);
        }
    }
}
