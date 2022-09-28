package com.example.cleanup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cleanup.model.EventModel;
import com.example.cleanup.model.RoomModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import static com.example.cleanup.CalendarUtils.daysInMonthArray;
import static com.example.cleanup.CalendarUtils.monthYearFromDate;
import static com.example.cleanup.CalendarUtils.selectedDate;

public class Events extends AppCompatActivity{
    BottomNavigationView bnv;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;
    FirebaseFirestore eventdocs;
    String id,schedule;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();
        bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bnv.setSelectedItemId(R.id.events);

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
    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this::onItemClick);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            CalendarUtils.selectedDate = date;
            setMonthView();
            //Toast.makeText(this, "" + date, Toast.LENGTH_SHORT).show();
            String dates = date.toString();
            ArrayList<String> as = new ArrayList<>();
            eventdocs = FirebaseFirestore.getInstance();
            eventdocs.collection("events")
                    .whereEqualTo("schedule", dates)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                as.clear();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    EventModel event = document.toObject(EventModel.class);
                                    as.add(" EVENT NAME: " + event.getEvent_name());
                                }
                                try{
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_selectable_list_item,as);
                                    adapter.notifyDataSetChanged();
                                    eventListView.setAdapter(adapter);
                                }catch (Exception e){
                                    Toast.makeText(Events.this, e.getMessage().toString() + date, Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Events.this, "Error!" + date, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }

    }
    private void setEventAdapter()
    {
        //ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        //EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        //eventListView.setAdapter(eventAdapter);
    }
    public void weeklyAction(View view)
    {
        startActivity(new Intent(this, WeekViewActivity.class));
    }
    public void onBackPressed() {

        Intent intent = new Intent(Events.this,
                MainActivity.class);
        startActivity(intent);

        return;
    }

}