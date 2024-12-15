package com.example.cs201_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class DOCTOR_login_page extends AppCompatActivity {

    private EditText editTextEmailLogin, editTextPasswordLogin;
    private Button buttonLogin;
    private DatabaseReference doctorDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login_page);

        // Initialize Firebase Database reference to "doctor_01"
        doctorDatabaseRef = FirebaseDatabase.getInstance().getReference("doctor_01"); // Ensure correct reference

        // Initialize views
        editTextEmailLogin = findViewById(R.id.editTextEmailLogin);
        editTextPasswordLogin = findViewById(R.id.editTextPasswordLogin);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Set up login button click listener
        buttonLogin.setOnClickListener(v -> login());
    }

    // Method to handle login
    private void login() {
        String email = editTextEmailLogin.getText().toString().trim();  // Trim to remove leading/trailing spaces
        String password = editTextPasswordLogin.getText().toString().trim();  // Trim to remove spaces

        // Validate input
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(DOCTOR_login_page.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Query the Firebase database to check if doctor exists
        doctorDatabaseRef.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Doctor found, now check password
                            for (DataSnapshot doctorSnapshot : dataSnapshot.getChildren()) {
                                doctor_01 doctor = doctorSnapshot.getValue(doctor_01.class);
                                if (doctor != null && doctor.getPassword().equals(password)) {
                                    // Successful login
                                    Intent intent = new Intent(DOCTOR_login_page.this, Doctor_Dashboard.class);
                                    startActivity(intent);
                                    finish();  // Close the login page so user cannot return to it
                                } else {
                                    // Invalid password
                                    Toast.makeText(DOCTOR_login_page.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            // Doctor not found in the database
                            Toast.makeText(DOCTOR_login_page.this, "Doctor not found with this email", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(DOCTOR_login_page.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
