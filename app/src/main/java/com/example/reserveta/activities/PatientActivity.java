package com.example.reserveta.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.reserveta.R;
import com.example.reserveta.adapter.SpecializationAdapter;
import com.example.reserveta.model.PatientModel;
import com.example.reserveta.utils.DataBase;

import java.util.ArrayList;
import java.util.Arrays;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        PatientModel user= (PatientModel) getIntent().getSerializableExtra(getString(R.string.user));
        RecyclerView recyclerView=findViewById(R.id.specialization_recycler);

        String [] strings=getResources().getStringArray(R.array.SpecializationList);
        ArrayList<String> specialization=new ArrayList<>(Arrays.asList(strings));
        specialization.remove(0);
        SpecializationAdapter adapter=new SpecializationAdapter(specialization,this,user);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
