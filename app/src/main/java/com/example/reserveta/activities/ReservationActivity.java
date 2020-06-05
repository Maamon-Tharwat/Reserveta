package com.example.reserveta.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.reserveta.R;
import com.example.reserveta.model.DoctorModel;
import com.example.reserveta.model.PatientModel;
import com.example.reserveta.utils.AppBrain;
import com.example.reserveta.utils.DataBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        PatientModel user= (PatientModel) getIntent().getSerializableExtra(getString(R.string.user));
        DoctorModel doctor= (DoctorModel) getIntent().getSerializableExtra(getString(R.string.doctor));

        TextView doctorTime=findViewById(R.id.reservation_doctor_available_time);
        doctorTime.setText("Doctor "+doctor.getUsername()+" opens from "+doctor.getStartHour()+" to "+doctor.getEndHour());

        long reservation=AppBrain.calculateReservationTime(this,doctor);

        TextView reservationTime=findViewById(R.id.reservation_time);
        reservationTime.setText(AppBrain.getCurrentDate(this,new Date(reservation)));

        Button reserve=findViewById(R.id.reservation_reserve);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase.addReservation(getApplicationContext(),doctor,user,reservation);
                Intent intent =new Intent(ReservationActivity.this,PatientActivity.class);
                intent.putExtra(getBaseContext().getString(R.string.user),user);
                startActivity(intent);
                finish();
            }
        });

    }

}
