package com.example.reserveta.model;

public class ReservationModel {
    private String doctorID;
    private String patientID;
    private long reservationTime;
    private String patientName;

    public ReservationModel(String doctorID, String patientID, long reservationTime, String patientName) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.reservationTime = reservationTime;
        this.patientName = patientName;
    }

    public ReservationModel() {
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public long getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(long reservationTime) {
        this.reservationTime = reservationTime;
    }
}
