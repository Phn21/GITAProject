package com.example.day11last;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.day11last.Database.Info1;

public class DetailInfo extends AppCompatActivity {

    TextView name, number, location, donationDay, reDonationDay, bloodType, healthIssues;
    Button callButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Rotation of Screen
        getSupportActionBar().hide();

        name = findViewById(R.id.nameDEInput);
        number = findViewById(R.id.numberDEInput);
        location = findViewById(R.id.locationDEInput);
        donationDay = findViewById(R.id.donationDayDEInput);
        reDonationDay = findViewById(R.id.redonationDEInput);
        bloodType = findViewById(R.id.bloodTypeDEInput);
        healthIssues = findViewById(R.id.healthDEInput);

        callButton = findViewById(R.id.callButton);

        Info1 info1 = (Info1) getIntent().getSerializableExtra("INFO");


        name.setText(info1.getName());
        number.setText(info1.getNumber());
        location.setText(info1.getLocation());
        donationDay.setText(info1.getDonationDay());
        reDonationDay.setText(info1.getAbleToDonateAgain());
        bloodType.setText(info1.getBloodType());
        if (info1.getHealthIssue().equals("Null")){
            healthIssues.setText("Don't have Health issue");
        } else healthIssues.setText(info1.getHealthIssue());


        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone(number.getText().toString());
            }
        });
    }
    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}