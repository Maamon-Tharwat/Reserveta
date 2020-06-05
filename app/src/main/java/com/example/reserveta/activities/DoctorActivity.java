package com.example.reserveta.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.reserveta.R;
import com.example.reserveta.adapter.DoctorTabAdapter;
import com.example.reserveta.model.DoctorModel;
import com.example.reserveta.utils.DataBase;
import com.google.android.material.tabs.TabLayout;

public class DoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        DoctorModel user= (DoctorModel) getIntent().getSerializableExtra(getString(R.string.user));

        ViewPager pager=findViewById(R.id.doctor_viewpager);
        DoctorTabAdapter adapter=new DoctorTabAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,this,user );
        pager.setAdapter(adapter);
        TabLayout tabLayout=findViewById(R.id.doctor_tablayout);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.sign_out_menu){
            DataBase.signOut(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.sign_out_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
