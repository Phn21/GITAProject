package com.example.day11last;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day11last.Database.DatabaseClient;
import com.example.day11last.Database.Info1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {

    EditText name, number, location, healthIssues, dateOfDonation, ableToDonateAgain;
    Button registration;
    Spinner spinner;
    CheckBox checkBox;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    String selectedBloodType, checkboxPoint;
    Button calendar, calendar1;
    String currentDate;
    Info1 info1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Rotation of Screen
        getSupportActionBar().hide();
        name = findViewById(R.id.nameED);
        number = findViewById(R.id.numberED);
        location = findViewById(R.id.locationED);
        healthIssues = findViewById(R.id.healthIssuesED);
        dateOfDonation = findViewById(R.id.giveBloodED);
        ableToDonateAgain = findViewById(R.id.ableToGiveED);
        registration = findViewById(R.id.registrationBT);
        spinner = findViewById(R.id.spinner);
        checkBox = findViewById(R.id.checkBox);

        calendar = findViewById(R.id.calendar);
        calendar1 = findViewById(R.id.calendar1);

        currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        int day = Integer.parseInt(currentDate.substring(0,2));
        int month = Integer.parseInt(currentDate.substring(3,5));
        int year = Integer.parseInt(currentDate.substring(6,10));

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpWithListener = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateOfDonation.setText(year + "/" + (month+1) + "/" + dayOfMonth);
                    }
                }, year, month-1, day);


                dpWithListener.show();
            }
        });

        calendar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpWithListener = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ableToDonateAgain.setText(year + "/" + (month+1) + "/" + dayOfMonth);
                    }
                }, year, month-1, day);


                dpWithListener.show();
            }
        });


        checkboxPoint = "0";
        arrayList = new ArrayList();

        arrayList.add("Blood Type");arrayList.add("AB+");arrayList.add("AB-");arrayList.add("A+");arrayList.add("A-");arrayList.add("B+");arrayList.add("B-");arrayList.add("O+");arrayList.add("O-");

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    healthIssues.setVisibility(View.VISIBLE);
                    checkboxPoint = "1";
                } else {
                    healthIssues.setVisibility(View.INVISIBLE);
                    checkboxPoint = "0";
                }
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty() || number.getText().toString().isEmpty() || location.getText().toString().isEmpty()
                        || dateOfDonation.getText().toString().isEmpty() || ableToDonateAgain.getText().toString().isEmpty())
                    Toast.makeText(SignUpActivity.this, "Fill the fields!", Toast.LENGTH_SHORT).show();
                else if (selectedBloodType.equals("Blood Type")){
                    Toast.makeText(SignUpActivity.this, "Select your blood type!", Toast.LENGTH_SHORT).show();
                }
                else {
                    GetInfo1 getInfo1 = new GetInfo1();
                    getInfo1.execute();
                }
            }
        });

        arrayAdapter = new ArrayAdapter(SignUpActivity.this, android.R.layout.simple_list_item_1,arrayList);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBloodType = spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });
    }


    class GetInfo1 extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            info1 = new Info1();

            info1.setName(name.getText().toString());
            info1.setNumber(number.getText().toString());
            info1.setLocation(location.getText().toString());
            info1.setDonationDay(dateOfDonation.getText().toString());
            info1.setBloodType(selectedBloodType);
            info1.setAbleToDonateAgain(ableToDonateAgain.getText().toString());
            info1.setPoint(checkboxPoint);
            if (checkboxPoint.equals("1")){
                info1.setHealthIssue(healthIssues.getText().toString());
            }else info1.setHealthIssue("Null");
            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().info1Dao().insert(info1);

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            Toast.makeText(SignUpActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
}