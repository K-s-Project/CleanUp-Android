package com.example.cleanup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeSecond extends AppCompatActivity {
    BottomNavigationView bnv;
    ImageView backhome;

    TextView building_number;
    TextView building_name;
    TextView schedule;
    TextView notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_second);
        building_number = findViewById(R.id.building_number);
        building_name = findViewById(R.id.building_name);
        notes = findViewById(R.id.notes);
        schedule = findViewById(R.id.schedule);


        String room_number1 = getIntent().getStringExtra("ROOM_NUMBER");
        String building_name1 = getIntent().getStringExtra("BUILDING_NAME");
        String id1 = getIntent().getStringExtra("ID");
        String notes1 = getIntent().getStringExtra("NOTES");
        String schedule1 = getIntent().getStringExtra("SCHEDULE");


        building_number.setText(room_number1);
        building_name.setText(building_name1);
        notes.setText(notes1);
        schedule.setText(schedule1);

        bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        backhome = (ImageView) findViewById(R.id.backhome);

        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Home.class));
                overridePendingTransition(0,0);
            }
        });
        bnv.setSelectedItemId(R.id.home);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.events:
                        startActivity(new Intent(getApplicationContext(),Events.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    public void onBackPressed() {

        Intent intent = new Intent(HomeSecond.this,
                MainActivity.class);
        startActivity(intent);

        return;
    }
}