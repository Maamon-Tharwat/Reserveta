package com.example.reserveta.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reserveta.R;
import com.example.reserveta.activities.ReservationActivity;
import com.example.reserveta.model.DoctorModel;
import com.example.reserveta.model.PatientModel;

import java.util.ArrayList;

public class SpecializationDoctorsAdapter extends RecyclerView.Adapter<SpecializationDoctorsAdapter.ViewHolder> {

    private ArrayList<DoctorModel> list;
    private Context context;
    private PatientModel user;

    public SpecializationDoctorsAdapter(ArrayList<DoctorModel> list, Context context, PatientModel user) {
        this.list = list;
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.specialization_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.doctorName.setText(list.get(position).getUsername());
        final DoctorModel doctor=list.get(position);
        holder.doctorName.setOnClickListener(v -> {
            Intent intent =new Intent(context, ReservationActivity.class);
            intent.putExtra(context.getString(R.string.doctor),doctor);
            intent.putExtra(context.getString(R.string.user),user);
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName=itemView.findViewById(R.id.specialization_list_text);
        }
    }
}
