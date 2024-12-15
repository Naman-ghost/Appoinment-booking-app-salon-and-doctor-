package com.example.cs201_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adapter class to bind the doctor data to the RecyclerView
public class SearchDoctorAdapter extends RecyclerView.Adapter<SearchDoctorAdapter.DoctorViewHolder> {

    private List<Doctor> doctorList;

    // Constructor to initialize the doctor list
    public SearchDoctorAdapter(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the list
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        // Get the doctor object at the current position
        Doctor doctor = doctorList.get(position);

        // Bind the data to the UI components
        holder.nameTextView.setText("Name: " + doctor.getName());
        holder.specialtyTextView.setText("Specialization: " + doctor.getSpecialization());
        holder.feeTextView.setText("Fee: ₹" + doctor.getFee());
    }

    @Override
    public int getItemCount() {
        // Return the size of the doctor list
        return doctorList != null ? doctorList.size() : 0;
    }

    // ViewHolder class to hold references to the UI components for each item
    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, specialtyTextView, feeTextView;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the TextViews
            nameTextView = itemView.findViewById(R.id.doctorName);
            specialtyTextView = itemView.findViewById(R.id.doctorSpecialty);
            feeTextView = itemView.findViewById(R.id.doctorFee);
        }
    }

    // Method to update the doctor list dynamically
    public void updateDoctorList(List<Doctor> newDoctorList) {
        this.doctorList = newDoctorList;
        notifyDataSetChanged(); // Notify the adapter to refresh the data
    }
}
