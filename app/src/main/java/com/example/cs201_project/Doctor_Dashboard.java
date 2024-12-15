package com.example.cs201_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Doctor_Dashboard extends AppCompatActivity {

    private RecyclerView recyclerViewAppointments;
    private AppointmentAdapter appointmentAdapter;
    private DatabaseReference appointmentsDatabase;
    private Button logoutButton;
    private List<Appointment> appointmentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_dashboard);

        // Set padding for insets (optional for better UI)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        recyclerViewAppointments = findViewById(R.id.recyclerViewAppointments);
        recyclerViewAppointments.setLayoutManager(new LinearLayoutManager(this));
        logoutButton = findViewById(R.id.buttonLogout);

        // Firebase references
        appointmentsDatabase = FirebaseDatabase.getInstance().getReference("Appointments");
        appointmentsList = new ArrayList<>();

        // Load appointments
        loadAppointments();

        // Handle logout button
        logoutButton.setOnClickListener(v -> {
            // Add your logout logic here
            Toast.makeText(Doctor_Dashboard.this, "Logging out...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Doctor_Dashboard.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void loadAppointments() {
        // Retrieve all appointments from Firebase
        appointmentsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                appointmentsList.clear(); // Clear the list to avoid duplicates
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = snapshot.getValue(Appointment.class);
                    if (appointment != null) {
                        appointmentsList.add(appointment);
                    }
                }

                // Update RecyclerView with the appointments
                if (appointmentAdapter == null) {
                    appointmentAdapter = new AppointmentAdapter(appointmentsList, Doctor_Dashboard.this);
                    recyclerViewAppointments.setAdapter(appointmentAdapter);
                } else {
                    appointmentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Doctor_Dashboard.this, "Failed to load appointments.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Handle accepting appointments
    public void acceptAppointment(String appointmentId) {
        appointmentsDatabase.child(appointmentId).child("status").setValue("Accepted")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Doctor_Dashboard.this, "Appointment accepted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Doctor_Dashboard.this, "Failed to accept appointment", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Handle rejecting appointments
    public void rejectAppointment(String appointmentId) {
        appointmentsDatabase.child(appointmentId).child("status").setValue("Rejected")
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Doctor_Dashboard.this, "Appointment rejected", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Doctor_Dashboard.this, "Failed to reject appointment", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
