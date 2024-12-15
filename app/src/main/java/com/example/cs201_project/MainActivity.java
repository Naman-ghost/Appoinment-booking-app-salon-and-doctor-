package com.example.cs201_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    // Mapping between display names and Firebase specialization names
    private final HashMap<String, String> categoryMapping = new HashMap<String, String>() {{
        put("Heart Surgery", "Cardiology");
        put("Teeth Surgery", "Dentist");
        put("Eye Surgery", "Ophthalmologist");
        put("Gynecology", "Gynecologist");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SharedPreferences to handle first-time launch
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isFirstLaunch = preferences.getBoolean("isFirstLaunch", true);

        // Check if it's the first launch
        if (isFirstLaunch) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstLaunch", false);
            editor.apply();
        }

        // Initialize DrawerLayout (if used in your layout)
        drawerLayout = findViewById(R.id.main);

        // Setup click listeners for the categories
        setupCategoryClickListeners();

        // Profile image click listener
        setupProfileClickListener();

        // Add click listener for Lab Tests
        setupLabTestsClickListener();

        // Add sample doctor data to Firebase
        addSampleDoctorData();

        // Setup the menu button click listener
        setupMenuButtonClickListener();

        // Setup the Barber button click listener
        setupBarberButtonClickListener();
    }

    private void setupCategoryClickListeners() {
        CardView heartSurgeryCard = findViewById(R.id.cardHeartSurgery);
        CardView teethSurgeryCard = findViewById(R.id.cardTeethSurgery);
        CardView eyeSurgeryCard = findViewById(R.id.cardEyeSurgery);
        CardView gynaecologistCard = findViewById(R.id.cardGynecology);

        heartSurgeryCard.setOnClickListener(view -> openCategory("Heart Surgery"));
        teethSurgeryCard.setOnClickListener(view -> openCategory("Teeth Surgery"));
        eyeSurgeryCard.setOnClickListener(view -> openCategory("Eye Surgery"));
        gynaecologistCard.setOnClickListener(view -> openCategory("Gynecology"));
    }

    private void setupLabTestsClickListener() {
        CardView labTestsCard = findViewById(R.id.cardLabTests);

        labTestsCard.setOnClickListener(view -> {
            Intent intent = new Intent(this, LabTestActivity.class);
            startActivity(intent);
        });
    }

    private void openCategory(String displayCategory) {
        String firebaseCategory = categoryMapping.get(displayCategory);

        if (firebaseCategory != null) {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra("category_name", firebaseCategory);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Category not found!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupProfileClickListener() {
        ImageView profileImage = findViewById(R.id.profile);
        profileImage.setOnClickListener(view -> openDisplayProfileActivity());
    }

    private void openDisplayProfileActivity() {
        Intent intent = new Intent(this, DisplayProfileActivity.class);
        startActivity(intent);
    }

    private void addSampleDoctorData() {
        DatabaseReference doctorRef = FirebaseDatabase.getInstance().getReference("Doctors");

        DoctorModel doctor1 = new DoctorModel(
                "doctorId1",
                "Dr. Naman",
                "Cardiology",
                "150",
                "9 AM - 5 PM",
                "Ramlala",
                Arrays.asList("9 AM - 10 AM", "10 AM - 11 AM", "11 AM - 12 PM", "1 PM - 2 PM")
        );

        DoctorModel doctor2 = new DoctorModel(
                "doctorId2",
                "Dr. Sita",
                "Dentist",
                "100",
                "10 AM - 4 PM",
                "Smile Clinic",
                Arrays.asList("10 AM - 11 AM", "12 PM - 1 PM", "3 PM - 4 PM")
        );

        DoctorModel doctor3 = new DoctorModel(
                "doctorId3",
                "Dr. Ravi",
                "Ophthalmologist",
                "200",
                "9 AM - 1 PM",
                "Eye Care",
                Arrays.asList("9 AM - 10 AM", "11 AM - 12 PM", "12 PM - 1 PM")
        );

        DoctorModel doctor4 = new DoctorModel(
                "doctorId4",
                "Dr. Priya",
                "Gynecologist",
                "300",
                "9 AM - 6 PM",
                "Women's Health",
                Arrays.asList("9 AM - 10 AM", "12 PM - 1 PM", "2 PM - 3 PM", "4 PM - 5 PM")
        );

        doctorRef.child("doctorId1").setValue(doctor1);
        doctorRef.child("doctorId2").setValue(doctor2);
        doctorRef.child("doctorId3").setValue(doctor3);
        doctorRef.child("doctorId4").setValue(doctor4);
    }

    private void setupMenuButtonClickListener() {
        ImageView menuButton = findViewById(R.id.menubtn);

        // When the menu button is clicked, exit the app
        menuButton.setOnClickListener(view -> {
            finish();  // Close the current activity
            System.exit(0);  // Terminate the application
        });
    }

    private void setupBarberButtonClickListener() {
        CardView barberCard = findViewById(R.id.Barber);

        barberCard.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Barber_Activity.class);
            startActivity(intent);
        });
    }
}
