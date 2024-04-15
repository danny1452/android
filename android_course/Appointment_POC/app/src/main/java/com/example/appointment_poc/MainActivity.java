package com.example.appointment_poc;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TimeSlotAdapter.OnTimeSlotClickListener{

    private TimeSlotDatabase timeSlotDatabase;
    private List<BookedTimeSlot> bookedTimeSlots;
    private TimeSlotAdapter timeSlotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Room database
        timeSlotDatabase = MyApplication.getTimeSlotDatabase();

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        new Thread(() -> {
            bookedTimeSlots = timeSlotDatabase.bookedTimeSlotDao().getAllBookedTimeSlots();
            runOnUiThread(() -> {
                // Initialize and set up the RecyclerView with time slots
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                timeSlotAdapter = new TimeSlotAdapter(generateTimeSlots(), bookedTimeSlots, this);
                recyclerView.setAdapter(timeSlotAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            });
        }).start();
    }

    private List<TimeSlot> generateTimeSlots() {
        List<TimeSlot> timeSlots = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Iterate over the next 15 days, skipping weekends
        for (int day = 0; day < 15; day++) {
            // Skip weekends (Saturday and Sunday)
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                calendar.add(Calendar.DAY_OF_WEEK, 1); // Move to the next day
                continue;
            }

            // Reset time to 10 am for each day
            calendar.set(Calendar.HOUR_OF_DAY, 10);
            calendar.set(Calendar.MINUTE, 0);

            // Iterate over each hour from 10 am to 5 pm
            while (calendar.get(Calendar.HOUR_OF_DAY) < 17) {
                timeSlots.add(new TimeSlot(calendar.get(Calendar.DAY_OF_WEEK), calendar.getTime()));
                calendar.add(Calendar.HOUR, 1);
            }

            // Move to the next day
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        return timeSlots;
    }

    // Implement the OnTimeSlotClickListener interface
    @Override
    public void onTimeSlotClick(TimeSlot timeSlot) {
        // Handle the click event, e.g., navigate to BookingActivity
        Intent intent = new Intent(MainActivity.this, BookingActivity.class);

        // Pass any relevant data to BookingActivity using intent extras
        intent.putExtra("selectedTimeSlot", timeSlot.getDateTime().toString());

        startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setupRecyclerView();
        }
    }
}