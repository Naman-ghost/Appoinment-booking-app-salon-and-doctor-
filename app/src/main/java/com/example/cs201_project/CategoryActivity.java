package com.example.cs201_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private DatabaseReference doctorDatabaseRef;
    private DatabaseReference appointmentDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        doctorDatabaseRef = FirebaseDatabase.getInstance().getReference("Doctors");
        appointmentDatabaseRef = FirebaseDatabase.getInstance().getReference("Appointments");

        String category = getIntent().getStringExtra("category_name");
        if (category != null) {
            setupDoctorCategories(category);
        } else {
            Toast.makeText(this, "Category not found!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupDoctorCategories(String category) {
        LinearLayout doctorList = findViewById(R.id.doctor_list);
        doctorList.removeAllViews();

        doctorDatabaseRef.orderByChild("specialization").equalTo(category)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Doctor> doctors = new ArrayList<>();
                        for (DataSnapshot doctorSnapshot : snapshot.getChildren()) {
                            Doctor doctor = doctorSnapshot.getValue(Doctor.class);
                            if (doctor != null) {
                                doctors.add(doctor);
                            }
                        }

                        if (doctors.isEmpty()) {
                            Toast.makeText(CategoryActivity.this, "No doctors available for this category.", Toast.LENGTH_SHORT).show();
                        }

                        for (Doctor doctor : doctors) {
                            doctorList.addView(createDoctorView(doctor));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CategoryActivity.this, "Failed to fetch doctors. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private View createDoctorView(Doctor doctor) {
        LinearLayout doctorView = new LinearLayout(this);
        doctorView.setOrientation(LinearLayout.VERTICAL);
        doctorView.setPadding(16, 16, 16, 16);

        TextView nameView = new TextView(this);
        nameView.setText("Name: " + doctor.getName());
        nameView.setTextSize(18);

        TextView specializationView = new TextView(this);
        specializationView.setText("Specialization: " + doctor.getSpecialization());

        TextView feeView = new TextView(this);
        feeView.setText("Fee: ₹" + doctor.getFee());

        TextView availabilityView = new TextView(this);
        availabilityView.setText("Available: " + doctor.getAvailability());

        TextView hospitalView = new TextView(this);
        hospitalView.setText("Hospital: " + doctor.getHospital());

        doctorView.addView(nameView);
        doctorView.addView(specializationView);
        doctorView.addView(feeView);
        doctorView.addView(availabilityView);
        doctorView.addView(hospitalView);

        // Add buttons for each available appointment slot
        if (doctor.getAppointments() != null) {
            for (String slot : doctor.getAppointments()) {
                Button bookButton = new Button(this);
                bookButton.setText("Book Appointment: " + slot);
                bookButton.setOnClickListener(v -> bookAppointment(doctor.getDoctorId(), slot));
                doctorView.addView(bookButton);
            }
        }

        return doctorView;
    }

    private void bookAppointment(String doctorId, String slot) {
        String userId = "user123";  // Replace with actual user ID from your authentication system
        String appointmentId = appointmentDatabaseRef.push().getKey();

        if (appointmentId != null) {
            // Create an Appointment object
            Appointment appointment = new Appointment(
                    appointmentId, doctorId, userId, slot, "Pending");

            // Save the appointment to Firebase
            appointmentDatabaseRef.child(appointmentId).setValue(appointment)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(CategoryActivity.this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show();
                            updateDoctorAvailability(doctorId, slot);
                        } else {
                            Toast.makeText(CategoryActivity.this, "Failed to book appointment.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void updateDoctorAvailability(String doctorId, String slot) {
        doctorDatabaseRef.child(doctorId).child("appointments").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        List<String> appointments = (List<String>) task.getResult().getValue();
                        if (appointments != null && appointments.contains(slot)) {
                            appointments.remove(slot); // Remove the booked slot
                            doctorDatabaseRef.child(doctorId).child("appointments").setValue(appointments);
                        }
                    }
                });
    }
}
