package com.example.cs201_project;

import java.util.List;

public class Doctor {

    private String doctorId;
    private String name;
    private String specialization;
    private String fee;
    private String availability;
    private String hospital;
    private List<String> appointments;

    public Doctor() {
        // Default constructor required for Firebase
    }

    public Doctor(String doctorId, String name, String specialization, String fee, String availability, String hospital, List<String> appointments) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.fee = fee;
        this.availability = availability;
        this.hospital = hospital;
        this.appointments = appointments;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getFee() {
        return fee;
    }

    public String getAvailability() {
        return availability;
    }

    public String getHospital() {
        return hospital;
    }

    public List<String> getAppointments() {
        return appointments;
    }
}
