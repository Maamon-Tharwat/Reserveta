package com.example.reserveta.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.reserveta.R;
import com.example.reserveta.fragments.DoctorReservationsFragment;
import com.example.reserveta.fragments.DoctorScheduleFragment;
import com.example.reserveta.model.DoctorModel;

public class DoctorTabAdapter extends FragmentPagerAdapter {

    private Context context;
    private DoctorModel user;

    public DoctorTabAdapter(@NonNull FragmentManager fm, int behavior, Context context, DoctorModel user) {
        super(fm, behavior);
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0) {
            return new DoctorReservationsFragment(user);
        }else {
            return new DoctorScheduleFragment(user);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return context.getString(R.string.reservations);
        }else{
            return context.getString(R.string.work_time);
        }
    }
}
