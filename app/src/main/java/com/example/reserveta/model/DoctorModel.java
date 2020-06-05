package com.example.reserveta.model;

import java.io.Serializable;

public class DoctorModel extends UserModel implements Serializable {

    private String specialization;
    private int startHour;
    private int endHour;
    private long examinationTime;
    private long lastReservation;

    public DoctorModel(String username, String email, String password, String phone, String type, String specialization) {
        super(username, email, password, phone, type);
        this.specialization = specialization;
        examinationTime=10;
    }

    public DoctorModel() { }

    public long getLastReservation() {
        return lastReservation;
    }

    public void setLastReservation(long lastReservation) {
        this.lastReservation = lastReservation;
    }

    public long getExaminationTime() {
        return examinationTime;
    }

    public void setExaminationTime(long examinationTime) {
        this.examinationTime = examinationTime;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
