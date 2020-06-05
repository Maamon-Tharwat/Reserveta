package com.example.reserveta.model;

import java.io.Serializable;

public class PatientModel extends UserModel implements Serializable {

    public PatientModel(String username, String email, String password, String phone, String type) {
        super(username, email, password, phone, type);
    }

    public PatientModel() {
    }


}
