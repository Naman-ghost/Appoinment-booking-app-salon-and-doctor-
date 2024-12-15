package com.example.cs201_project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class DisplayProfileActivity extends AppCompatActivity {

    private TextView textViewName, textViewEmail;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);

        reference = FirebaseDatabase.getInstance().getReference("Patients"); // Reference to 'Patients' database
        loadUserDetails();
    }

    private void loadUserDetails() {
        String userId = getUserId();

        if (userId.isEmpty()) {
            Toast.makeText(this, "User ID not found in preferences", Toast.LENGTH_SHORT).show();
            return;
        }

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);

                    textViewName.setText("Name: " + name);
                    textViewEmail.setText("Email: " + email);
                } else {
                    Toast.makeText(DisplayProfileActivity.this, "Error from Server ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DisplayProfileActivity.this, "Error retrieving data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getUserId() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return preferences.getString("userId", "");
    }
}
