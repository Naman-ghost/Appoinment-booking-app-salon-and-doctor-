package com.example.cs201_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private List<Doctor> doctorList; // List of Doctor objects

    public DoctorAdapter(List<Doctor> doctorList) {
        this.doctorList = doctorList; // Initialize with provided doctor list
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.doctorName.setText(doctor.getName()); // Set doctor's name
        holder.doctorSpecialty.setText(doctor.getSpecialization()); // Set doctor's specialty
    }

    @Override
    public int getItemCount() {
        return doctorList.size(); // Return the size of the doctor list
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView doctorName;
        TextView doctorSpecialty;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(android.R.id.text1); // Replace with your actual TextView ID
            doctorSpecialty = itemView.findViewById(android.R.id.text2); // Replace with your actual TextView ID
        }
    }
}
