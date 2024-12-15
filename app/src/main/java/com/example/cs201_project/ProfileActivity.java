package com.example.cs201_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button buttonSave;
    private TextView buttonLogin;

    private DatabaseReference patientDatabase; // Firebase Realtime Database reference
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase components
        mAuth = FirebaseAuth.getInstance();
        patientDatabase = FirebaseDatabase.getInstance().getReference("Patients");

        // Initialize UI components
        editTextName = findViewById(R.id.editTextName21);
        editTextEmail = findViewById(R.id.editTextEmail21);
        editTextPassword = findViewById(R.id.editTextPassword21);
        buttonSave = findViewById(R.id.buttonSave21);
        buttonLogin = findViewById(R.id.buttonLogin21);

        // Handle Save button click
        buttonSave.setOnClickListener(v -> registerAndSavePatient());

        // Handle Login button click
        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void registerAndSavePatient() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();
                            savePatientToFirebase(userId, name, email);
                        }
                    } else {
                        Toast.makeText(this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("AuthError", "Error: ", task.getException());
                    }
                });
    }

    private void savePatientToFirebase(String userId, String name, String email) {
        Patient patient = new Patient(userId, name, email);

        patientDatabase.child(userId).setValue(patient)
                .addOnSuccessListener(aVoid -> {
                    Log.d("FirebaseSave", "Patient saved successfully");
                    navigateToMainActivity(); // Only navigate on successful save
                })
                .addOnFailureListener(e -> {
                    Log.e("FirebaseSave", "Failed to save patient", e);
                    Toast.makeText(this, "Failed to save patient data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }


    private void saveUserDetailsToPreferences(String name, String email, String userId) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("userId", userId);
        editor.apply();
    }

    private void navigateToMainActivity() {
        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish current activity
    }

    // Patient model class
    public static class Patient {
        public String userId;
        public String name;
        public String email;

        public Patient() {
            // Default constructor required for calls to DataSnapshot.getValue(Patient.class)
        }

        public Patient(String userId, String name, String email) {
            this.userId = userId;
            this.name = name;
            this.email = email;
        }
    }
}
