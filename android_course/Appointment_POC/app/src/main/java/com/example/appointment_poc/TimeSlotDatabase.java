package com.example.appointment_poc;

// TimeSlotDatabase.java
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BookedTimeSlot.class}, version = 1)
public abstract class TimeSlotDatabase extends RoomDatabase {

    public abstract BookedTimeSlotDao bookedTimeSlotDao();
}
