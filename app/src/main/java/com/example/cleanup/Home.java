package com.example.cleanup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class Home extends AppCompatActivity {
    BottomNavigationView bnv;
    Button seemore;
    FirebaseAuth mAuth;

    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;
    // Next, prepare your data set. Create two string arrays for program name and program description respectively.
    String[] programNameList1 = {"C", "C++", "Java", "Android", "HTML5", "CSS3", "JavaScript", "jQuery", "Bootstrap", "PHP",
            "MySQL", "CodeIgniter", "React", "NodeJS", "AngularJS", "PostgreSQL", "Python", "C#", "Wordpress", "GitHub"};
    String[] programNameList2 = {"C", "C++", "Java", "Android", "HTML5", "CSS3", "JavaScript", "jQuery", "Bootstrap", "PHP",
            "MySQL", "CodeIgniter", "React", "NodeJS", "AngularJS", "PostgreSQL", "Python", "C#", "Wordpress", "GitHub"};
    String[] programNameList3 = {"C", "C++", "Java", "Android", "HTML5", "CSS3", "JavaScript", "jQuery", "Bootstrap", "PHP",
            "MySQL", "CodeIgniter", "React", "NodeJS", "AngularJS", "PostgreSQL", "Python", "C#", "Wordpress", "GitHub"};
//    String[] programDescriptionList = {"C Description", "C++ Description", "Java Description",
//            "Android Description", "HTML5 Description",
//            "CSS3 Description", "JavaScript Description", "jQuery Description",
//            "Bootstrap Description", "PHP Description", "MySQL Description",
//            "CodeIgniter Description", "React Description", "NodeJS Description",
//            "AngularJS Description", "PostgreSQL Description", "Python Description",
//            "C# Description", "Wordpress Description", "GitHub Description"};

    int[] programImages1 = {R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24};
    int[] programImages2 = {R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24};
    int[] programImages3 = {R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24,
            R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24, R.drawable.ic_baseline_arrow_back_24};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Obtain a handle for the RecyclerView
        recyclerView = findViewById(R.id.rvProgram);
        // You may use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Create an instance of ProgramAdapter. Pass context and all the array elements to the constructor
        programAdapter = new ProgramAdapter(this, programNameList1,programNameList2,programNameList3, programImages1,programImages2,programImages3);
        // Finally, attach the adapter with the RecyclerView
        recyclerView.setAdapter(programAdapter);
        bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        seemore = (Button) findViewById(R.id.seemore);
        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),HomeSecond.class));
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

        Intent intent = new Intent(Home.this,
                MainActivity.class);
        startActivity(intent);

        return;
    }
}