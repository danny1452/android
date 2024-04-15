package com.example.appointment_poc;

// TimeSlot.java

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class TimeSlot implements Parcelable {

    private int dayOfWeek; // Calendar day of week (e.g., Calendar.MONDAY)
    private Date dateTime;  // Date and time of the time slot

    public TimeSlot(int dayOfWeek, Date dateTime) {
        this.dayOfWeek = dayOfWeek;
        this.dateTime = dateTime;
    }

    protected TimeSlot(Parcel in) {
        dayOfWeek = in.readInt();
        dateTime = new Date(in.readLong());
    }

    public static final Creator<TimeSlot> CREATOR = new Creator<TimeSlot>() {
        @Override
        public TimeSlot createFromParcel(Parcel in) {
            return new TimeSlot(in);
        }

        @Override
        public TimeSlot[] newArray(int size) {
            return new TimeSlot[size];
        }
    };

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public Date getDateTime() {
        return dateTime;
    }

    // Parcelable implementation

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(dayOfWeek);
        dest.writeLong(dateTime.getTime());
    }
}
