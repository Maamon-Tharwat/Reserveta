package com.example.reserveta.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.reserveta.R;
import com.example.reserveta.model.DoctorModel;
import com.example.reserveta.utils.DataBase;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.ramotion.fluidslider.FluidSlider;

import kotlin.Unit;

public class DoctorScheduleFragment extends Fragment {

    private DoctorModel user;

    public DoctorScheduleFragment() {
        // Required empty public constructor
    }

    public DoctorScheduleFragment(DoctorModel user) {
        this.user = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_doctor_schedule, container, false);
        final TextView start=rootView.findViewById(R.id.doctor_schedule_start_hour);
        final TextView end=rootView.findViewById(R.id.doctor_schedule_end_hour);
        RangeSeekBar rangeSeekbar=rootView.findViewById(R.id.doctor_schedule_available_hours);

        rangeSeekbar.setOnRangeChangedListener(new OnRangeChangedListener() {
                    @Override
                    public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                        start.setText(String.valueOf((int)leftValue));
                        end.setText(String.valueOf((int)rightValue));
                    }

                    @Override
                    public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

                    }

                    @Override
                    public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

                    }
                }
        );

        FluidSlider slider=rootView.findViewById(R.id.doctor_schedule_examination);
        slider.setPositionListener(pos -> {
            final String value = String.valueOf( (int)((pos*50) +10) );
            slider.setBubbleText(value);
            return Unit.INSTANCE;
        });

        rangeSeekbar.setProgress(user.getStartHour(),user.getEndHour());

        slider.setPosition((float) ((user.getExaminationTime()-10.0)/50));

        Button submit=rootView.findViewById(R.id.doctor_schedule_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setStartHour(Integer.parseInt((String) start.getText()));
                user.setEndHour(Integer.parseInt((String) end.getText()));
                user.setExaminationTime(Long.parseLong(slider.getBubbleText()));
                DataBase.updateUser(getContext(),user);
            }
        });

        return rootView;
    }
}
