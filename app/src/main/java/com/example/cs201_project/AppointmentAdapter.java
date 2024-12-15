package com.example.cs201_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private List<Appointment> appointments;
    private Doctor_Dashboard doctorDashboard;

    public AppointmentAdapter(List<Appointment> appointments, Doctor_Dashboard doctorDashboard) {
        this.appointments = appointments;
        this.doctorDashboard = doctorDashboard;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each appointment item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);

        // Set the text for each field in the ViewHolder
        holder.patientName.setText("Patient: " + appointment.getUserId());  // Assuming getUserId() is available
        holder.appointmentSlot.setText("Slot: " + appointment.getSlot());  // Assuming getSlot() is available
        holder.status.setText("Status: " + appointment.getStatus());

        // Show/hide buttons based on appointment status
        if ("Pending".equals(appointment.getStatus())) {
            holder.acceptButton.setVisibility(View.VISIBLE);
            holder.rejectButton.setVisibility(View.VISIBLE);

            // Accept button logic
            holder.acceptButton.setOnClickListener(v -> {
                doctorDashboard.acceptAppointment(appointment.getAppointmentId());
            });

            // Reject button logic
            holder.rejectButton.setOnClickListener(v -> {
                doctorDashboard.rejectAppointment(appointment.getAppointmentId());
            });
        } else {
            // Hide buttons for accepted or rejected appointments
            holder.acceptButton.setVisibility(View.GONE);
            holder.rejectButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView patientName, appointmentSlot, status;
        Button acceptButton, rejectButton;

        public ViewHolder(View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patientName99);
            appointmentSlot = itemView.findViewById(R.id.appointmentSlot99);
            status = itemView.findViewById(R.id.status99);
            acceptButton = itemView.findViewById(R.id.acceptButton99);
            rejectButton = itemView.findViewById(R.id.rejectButton99);
        }
    }
}
