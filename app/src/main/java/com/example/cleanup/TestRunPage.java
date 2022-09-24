package com.example.cleanup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TestRunPage extends AppCompatActivity {
    BottomNavigationView bnv;
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
        setContentView(R.layout.activity_test_run_page);

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

        Intent intent = new Intent(TestRunPage.this,
                MainActivity.class);
        startActivity(intent);

        return;
    }
}