package com.example.appointment_poc;

// BookedTimeSlotDao.java
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookedTimeSlotDao {

    @Insert
    void insert(BookedTimeSlot bookedTimeSlot);

    @Query("SELECT * FROM booked_time_slots")
    List<BookedTimeSlot> getAllBookedTimeSlots();
}
