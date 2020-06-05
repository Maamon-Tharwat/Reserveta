package com.example.reserveta.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.reserveta.R;
import com.example.reserveta.adapter.SpecializationDoctorsAdapter;
import com.example.reserveta.model.DoctorModel;
import com.example.reserveta.model.PatientModel;
import com.example.reserveta.utils.DataBase;

import java.util.ArrayList;

public class SpecializationDoctorsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialization_doctors);

        PatientModel user= (PatientModel) getIntent().getSerializableExtra(getString(R.string.user));

        RecyclerView recyclerView = findViewById(R.id.specialization_doctor_recycler);

        ArrayList<DoctorModel> doctors = new ArrayList<>();

        SpecializationDoctorsAdapter adapter = new SpecializationDoctorsAdapter(doctors, this,user);

        String specialization = getIntent().getStringExtra(getString(R.string.specialization));

        DataBase.getAllSpecializationDoctors(this,specialization, adapter, doctors);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
