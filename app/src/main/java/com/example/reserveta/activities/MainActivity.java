package com.example.reserveta.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.reserveta.R;
import com.example.reserveta.utils.DataBase;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signIn=findViewById(R.id.sign_in);
        signIn.setOnClickListener(v -> {
            Intent intent =new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        });
        Button signUp=findViewById(R.id.sign_up);
        signUp.setOnClickListener(v -> {
            Intent intent =new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DataBase.checkAuth(this);
    }
}
