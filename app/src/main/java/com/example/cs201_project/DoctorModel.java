package com.example.cs201_project;

import java.util.List;

public class DoctorModel {
    private String doctorId;
    private String name;
    private String specialization;
    private String fee;
    private String availability;
    private String hospital;
    private List<String> appointments; // List of appointment times

    // Default constructor (required for Firebase)
    public DoctorModel() {}

    // Constructor with fields
    public DoctorModel(String doctorId, String name, String specialization, String fee, String availability, String hospital, List<String> appointments) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.fee = fee;
        this.availability = availability;
        this.hospital = hospital;
        this.appointments = appointments;
    }

    // Getters and setters
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public List<String> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<String> appointments) {
        this.appointments = appointments;
    }
}
