package com.example.day11last;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.day11last.Database.DatabaseClient;
import com.example.day11last.Database.Info1;
import com.example.day11last.Recycler.RVAdapter;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    String bloodType;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Rotation of Screen
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerView);
        spinner = findViewById(R.id.spinner2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
        arrayList = new ArrayList();

        arrayList.add("All Blood Type");arrayList.add("AB+");arrayList.add("AB-");arrayList.add("A+");arrayList.add("A-");arrayList.add("B+");arrayList.add("B-");arrayList.add("O+");arrayList.add("O-");

        arrayAdapter = new ArrayAdapter(UsersActivity.this, android.R.layout.simple_list_item_1,arrayList);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodType = spinner.getSelectedItem().toString();
                if (bloodType.equals("All Blood Type")){
                    GetAll getAll = new GetAll();
                    getAll.execute();
                }else {
                    GetAllByBlood getAllByBlood = new GetAllByBlood();
                    getAllByBlood.execute();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });
    }

    class GetAll extends AsyncTask<Void,Void, List<Info1>>{

        @Override
        protected List<Info1> doInBackground(Void... voids) {

            List<Info1> info1List = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().info1Dao().getAll();

            return info1List;
        }

        @Override
        protected void onPostExecute(List<Info1> info1s) {
            super.onPostExecute(info1s);
            RVAdapter rvAdapter = new RVAdapter(UsersActivity.this,info1s);
            recyclerView.setAdapter(rvAdapter);
        }
    }

    class GetAllByBlood extends AsyncTask<Void,Void, List<Info1>>{

        @Override
        protected List<Info1> doInBackground(Void... voids) {

            List<Info1> info1List = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().info1Dao().getInfoByBloodType(bloodType);

            return info1List;
        }

        @Override
        protected void onPostExecute(List<Info1> info1s) {
            super.onPostExecute(info1s);
            RVAdapter rvAdapter = new RVAdapter(UsersActivity.this,info1s);
            recyclerView.setAdapter(rvAdapter);
        }
    }
}