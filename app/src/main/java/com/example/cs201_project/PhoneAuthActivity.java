package com.example.cs201_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhoneAuthActivity extends AppCompatActivity {

    private Button buttonDoctorSignUp, buttonPatientSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        // Initialize the buttons
        buttonDoctorSignUp = findViewById(R.id.buttonDoctorSignUp);
        buttonPatientSignUp = findViewById(R.id.buttonPatientSignUp);

        buttonDoctorSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneAuthActivity.this, Doctors_sign_up_page.class);
                startActivity(intent);
            }
        });

        buttonPatientSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneAuthActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
