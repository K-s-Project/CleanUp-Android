package com.example.cleanup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.cleanup.model.RoomInterface;
import com.example.cleanup.model.RoomModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;


public class Home extends AppCompatActivity implements RoomInterface {
    BottomNavigationView bnv;
    FirebaseAuth mAuth;

    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;
    // Next, prepare your data set. Create two string arrays for program name and program description respectively.
   ArrayList<RoomModel> adapterRooms = new ArrayList<RoomModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Get Users Doc Refererence
        DocumentReference usersDoc = FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getUid());


        // Obtain a handle for the RecyclerView
        recyclerView = findViewById(R.id.rvProgram);
        // You may use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Create an instance of ProgramAdapter. Pass context and all the array elements to the constructor
        programAdapter = new ProgramAdapter(Home.this, adapterRooms, this);
        // Finally, attach the adapter with the RecyclerView
        recyclerView.setAdapter(programAdapter);

        String uid = FirebaseAuth.getInstance().getUid();
        CollectionReference roomDocs = FirebaseFirestore.getInstance().collection("rooms");

        roomDocs.whereArrayContainsAny("assignedStaff", Collections.singletonList("" + uid)).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentChange dc: value.getDocumentChanges()){
                    RoomModel room = dc.getDocument().toObject(RoomModel.class);
                    adapterRooms.add(room);
                }

                programAdapter.notifyDataSetChanged();
            }


        });



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

        Intent intent = new Intent(Home.this,
                MainActivity.class);
        startActivity(intent);

        return;
    }

    @Override
    public void onItemClick(int position) {
Intent intent = new Intent(Home.this,HomeSecond.class);

        intent.putExtra("ROOM_NUMBER",adapterRooms.get(position).room_number);
        intent.putExtra("BUILDING_NAME",adapterRooms.get(position).building_name);
        intent.putExtra("ID",adapterRooms.get(position).id);
        intent.putExtra("NOTES",adapterRooms.get(position).notes);
        intent.putExtra("SCHEDULE",adapterRooms.get(position).schedule);


        startActivity(intent);

    }
}