package com.example.cs201_project;

public class Appointment {

    private String appointmentId;
    private String doctorId;
    private String userId;
    private String slot;
    private String status;

    public Appointment() {
        // Default constructor required for Firebase
    }

    public Appointment(String appointmentId, String doctorId, String userId, String slot, String status) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.userId = userId;
        this.slot = slot;
        this.status = status;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSlot() {
        return slot;
    }

    public String getStatus() {
        return status;
    }
}
