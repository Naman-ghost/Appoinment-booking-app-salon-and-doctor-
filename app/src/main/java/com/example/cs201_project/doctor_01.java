package com.example.cs201_project;

import java.util.List;

public class doctor_01 {

    private String doctorId;
    private String name;
    private String specialization;
    private String fee;
    private String availability;
    private String hospital;
    private List<String> appointments;
    private String email;   // Added email for login validation
    private String password; // Added password for authentication

    public doctor_01() {
        // Default constructor required for Firebase
    }

    public doctor_01(String doctorId, String name, String specialization, String fee, String availability,
                     String hospital, List<String> appointments, String email, String password) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.fee = fee;
        this.availability = availability;
        this.hospital = hospital;
        this.appointments = appointments;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
