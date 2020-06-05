package com.example.reserveta.model;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String username;
    private String email;
    private String password;
    private String phone;
    private String type;
    private String UID;
    private String specialization;

    UserModel(String username, String email, String password, String phone, String type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.type = type;
    }

    UserModel() {
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
