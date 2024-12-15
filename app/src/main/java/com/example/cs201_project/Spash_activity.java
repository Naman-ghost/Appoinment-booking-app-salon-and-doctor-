package com.example.cs201_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Spash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash); // Ensure you have the correct XML layout file (activity_spash.xml)

        // Set padding for system bars (status and navigation bars) on a specific view, like the root layout or logo
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.logo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // Set a delay to navigate to the PhoneAuthActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Spash_activity.this, PhoneAuthActivity.class);
            startActivity(intent);
            finish();
        }, 3000); // 3-second delay
    }
}
