package com.example.reserveta.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.reserveta.R;
import com.example.reserveta.model.DoctorModel;
import com.example.reserveta.model.PatientModel;
import com.example.reserveta.utils.DataBase;

public class SignUpActivity extends AppCompatActivity {

    private Spinner specialization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        RadioButton doctor=findViewById(R.id.signup_doctor);
        doctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                specialization=findViewById(R.id.signup_specialization);
                if(isChecked){
                    specialization.setVisibility(View.VISIBLE);
                }else{
                    specialization.setVisibility(View.GONE);

                }
            }
        });

        Button signUp=findViewById(R.id.signup_sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username=findViewById(R.id.signup_username);
                EditText email=findViewById(R.id.signup_email);
                EditText password=findViewById(R.id.signup_password);
                EditText phone=findViewById(R.id.signup_phone);
                RadioGroup type=findViewById(R.id.signup_type_group);
                RadioButton chosenType=findViewById(type.getCheckedRadioButtonId());

                String strUsername=username.getText().toString();
                String strEmail=email.getText().toString();
                String strPassword=password.getText().toString();
                String strPhone=phone.getText().toString();
                String strType=chosenType.getText().toString();



                if(specialization!=null && specialization.getVisibility()!= View.GONE){
                    String strSpecialization=specialization.getSelectedItem().toString();
                    DoctorModel user= new DoctorModel(strUsername,strEmail,strPassword,strPhone,strType,strSpecialization);
                    DataBase.signUp(getApplicationContext(),user);
                }else {
                    PatientModel user=new PatientModel(strUsername,strEmail,strPassword,strPhone,strType);
                    DataBase.signUp(getApplicationContext(),user);
                }


            }
        });
    }
}
