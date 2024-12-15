package com.example.cs201_project;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs201_project.R;

public class Barber_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);

        int[] buttonIds = {
                R.id.btn_rahul_cutting, R.id.btn_rahul_shaving,
                R.id.btn_sameer_headmassage, R.id.btn_sameer_haircolor,
                R.id.btn_arjun_weddingpackage, R.id.btn_arjun_hairstraightening,
                R.id.btn_karan_cutting, R.id.btn_karan_shaving,
                R.id.btn_aman_headmassage, R.id.btn_aman_haircolor,
                R.id.btn_rohit_weddingpackage, R.id.btn_rohit_hairstraightening
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String serviceName = ((Button) v).getText().toString();
                    showConfirmationDialog(serviceName);
                }
            });
        }
    }

    private void showConfirmationDialog(String serviceName) {
        new AlertDialog.Builder(Barber_Activity.this)
                .setTitle("Confirm Order")
                .setMessage("Are you sure you want to book the service: " + serviceName + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Simulating order acceptance
                        Toast.makeText(Barber_Activity.this, serviceName + " Order Accepted!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
