package com.example.cs201_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Doctors_sign_up_page extends AppCompatActivity {

    // Firebase database reference
    private DatabaseReference doctorDatabase;

    // UI components for doctor details
    private EditText editTextName, editTextFee, editTextAvailability, editTextHospital;
    private Spinner spinnerSpecialization;
    private Button buttonSave;
    private TextView textViewLoginRedirect;  // Added to handle the login redirect

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_sign_up_page);

        // Initialize Firebase Database
        doctorDatabase = FirebaseDatabase.getInstance().getReference("doctors");

        // Initialize UI components
        editTextName = findViewById(R.id.editTextName_doctor);
        editTextFee = findViewById(R.id.editTextFee_doctor);
        editTextAvailability = findViewById(R.id.editTextAvailability_doctor);
        editTextHospital = findViewById(R.id.editTextHospital_doctor);
        spinnerSpecialization = findViewById(R.id.spinnerSpecialization_doctor);
        buttonSave = findViewById(R.id.buttonSave_doctor);
        textViewLoginRedirect = findViewById(R.id.textViewLoginRedirect);  // Initialize login redirect TextView

        // Set up the Spinner with specializations
        ArrayAdapter<CharSequence> specializationAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.specialization_array, // The array of specializations from strings.xml
                android.R.layout.simple_spinner_item // The layout for the spinner items
        );
        specializationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpecialization.setAdapter(specializationAdapter);

        // Save button listener to add doctor details to Firebase
        buttonSave.setOnClickListener(v -> addDoctorToFirebase());

        // TextView click listener for login redirection
        textViewLoginRedirect.setOnClickListener(v -> {
            // Navigate to the login page when clicked
            Intent intent = new Intent(Doctors_sign_up_page.this, DOCTOR_login_page.class);
            startActivity(intent);
        });
    }

    private void addDoctorToFirebase() {
        String name = editTextName.getText().toString().trim();
        String fee = editTextFee.getText().toString().trim();
        String availability = editTextAvailability.getText().toString().trim();
        String hospital = editTextHospital.getText().toString().trim();
        String specialization = (spinnerSpecialization.getSelectedItem() != null)
                ? spinnerSpecialization.getSelectedItem().toString()
                : "";

        // Basic validation
        if (name.isEmpty() || fee.isEmpty() || availability.isEmpty() || hospital.isEmpty() || specialization.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a unique ID for the doctor and save details in Firebase
        String doctorId = doctorDatabase.push().getKey();
        Doctor doctor = new Doctor(name, specialization, fee, availability, hospital);

        if (doctorId != null) {
            doctorDatabase.child(doctorId).setValue(doctor)
                    .addOnSuccessListener(aVoid -> {
                        // Show success message
                        Toast.makeText(this, "Doctor added successfully", Toast.LENGTH_SHORT).show();

                        // After successful data entry, navigate to the Doctor Dashboard
                        Intent intent = new Intent(Doctors_sign_up_page.this, Doctor_Dashboard.class);
                        startActivity(intent);  // Start the Doctor_dashboard activity
                        finish();  // Optionally, finish the current activity so the user can't go back to it
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Failed to add doctor", Toast.LENGTH_SHORT).show());
        }
    }

    // Doctor model class
    public static class Doctor {
        public String name;
        public String specialization;
        public String fee;
        public String availability;
        public String hospital;

        public Doctor() {
            // Default constructor required for calls to DataSnapshot.getValue(Doctor.class)
        }

        public Doctor(String name, String specialization, String fee, String availability, String hospital) {
            this.name = name;
            this.specialization = specialization;
            this.fee = fee;
            this.availability = availability;
            this.hospital = hospital;
        }
    }
}
