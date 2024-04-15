package com.example.appointment_poc;

// MyApplication.java
import android.app.Application;
import androidx.room.Room;

public class MyApplication extends Application {

    private static TimeSlotDatabase timeSlotDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Room database
        timeSlotDatabase = Room.databaseBuilder(getApplicationContext(),
                        TimeSlotDatabase.class, "time_slot_database")
                .build();
    }

    public static TimeSlotDatabase getTimeSlotDatabase() {
        return timeSlotDatabase;
    }
}
