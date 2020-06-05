package com.example.reserveta.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reserveta.R;
import com.example.reserveta.adapter.ReservationAdapter;
import com.example.reserveta.model.DoctorModel;
import com.example.reserveta.model.ReservationModel;
import com.example.reserveta.utils.DataBase;

import java.util.ArrayList;


public class DoctorReservationsFragment extends Fragment {

    DoctorModel user;

    public DoctorReservationsFragment(DoctorModel user) {
        this.user = user;
    }

    public DoctorReservationsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_doctor_reservations, container, false);

        RecyclerView recyclerView=rootView.findViewById(R.id.doctor_reservation_recycler);

        ArrayList<ReservationModel> list=new ArrayList<>();

        ReservationAdapter adapter=new ReservationAdapter(list,getContext());
        DataBase.getDoctorReservations(getContext(),user,adapter,list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }
}
