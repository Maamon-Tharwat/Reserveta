package com.example.reserveta.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.reserveta.R;
import com.example.reserveta.utils.DataBase;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button login=findViewById(R.id.login_sign_in);
        login.setOnClickListener(v -> {
            EditText username=findViewById(R.id.login_email);
            EditText password=findViewById(R.id.login_password);
            String email=username.getText().toString();
            String strPassword=password.getText().toString();
            DataBase.signIn(getApplicationContext(),email,strPassword);
        });
    }
}
