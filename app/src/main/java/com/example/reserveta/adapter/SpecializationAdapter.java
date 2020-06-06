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
import com.example.reserveta.activities.SpecializationDoctorsActivity;
import com.example.reserveta.model.PatientModel;

import java.util.ArrayList;

public class SpecializationAdapter extends RecyclerView.Adapter<SpecializationAdapter.ViewHolder> {
    private ArrayList<String> specialization;
    private Context context;
    private PatientModel user;

    public SpecializationAdapter(ArrayList<String> specialization, Context context, PatientModel user) {
        this.specialization = specialization;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(specialization.get(position));
        holder.textView.setOnClickListener(v -> {
            Intent intent =new Intent(context, SpecializationDoctorsActivity.class);
            intent.putExtra(context.getString(R.string.specialization),specialization.get(position));
            intent.putExtra(context.getString(R.string.user),user);
            context.startActivity(intent);
            //Toast.makeText(context,specialization.get(position),Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return specialization.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.specialization_list_text);
        }
    }
}
