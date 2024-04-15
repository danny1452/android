package com.example.appointment_poc;

// BookedTimeSlot.java
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "booked_time_slots")
public class BookedTimeSlot {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String dateTime;

    public void setId(int id) {
        this.id = id;
    }

    public BookedTimeSlot(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
