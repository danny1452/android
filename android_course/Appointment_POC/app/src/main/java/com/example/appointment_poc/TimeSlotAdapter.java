package com.example.appointment_poc;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items; // List containing both DateHeaderItem and TimeSlotItem
    private List<BookedTimeSlot> bookedTimeSlots;
    private OnTimeSlotClickListener onTimeSlotClickListener;

    private static final int DATE_HEADER_TYPE = 0;
    private static final int TIME_SLOT_TYPE = 1;

    public TimeSlotAdapter(List<TimeSlot> timeSlots, List<BookedTimeSlot> bookedTimeSlots, OnTimeSlotClickListener listener) {
        this.items = createItems(timeSlots);
        this.bookedTimeSlots = bookedTimeSlots;
        this.onTimeSlotClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == DATE_HEADER_TYPE) {
            View dateHeaderView = inflater.inflate(R.layout.item_date_header, parent, false);
            return new DateHeaderViewHolder(dateHeaderView);
        } else {
            View timeSlotView = inflater.inflate(R.layout.item_time_slot, parent, false);
            return new TimeSlotViewHolder(timeSlotView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == DATE_HEADER_TYPE) {
            DateHeaderViewHolder dateHeaderViewHolder = (DateHeaderViewHolder) holder;
            Date date = ((DateHeaderItem) items.get(position)).getDate();
            dateHeaderViewHolder.bindDateHeader(date);
        } else {
            TimeSlotViewHolder timeSlotViewHolder = (TimeSlotViewHolder) holder;
            TimeSlot timeSlot = ((TimeSlotItem) items.get(position)).getTimeSlot();
            timeSlotViewHolder.bindTimeSlot(timeSlot, isTimeSlotBooked(timeSlot));

            timeSlotViewHolder.itemView.setOnClickListener(v -> {
                if (onTimeSlotClickListener != null) {
                    onTimeSlotClickListener.onTimeSlotClick(timeSlot);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof DateHeaderItem) {
            return DATE_HEADER_TYPE;
        } else {
            return TIME_SLOT_TYPE;
        }
    }

    private List<Object> createItems(List<TimeSlot> timeSlots) {
        List<Object> items = new ArrayList<>();
        Date currentDate = null;

        for (TimeSlot timeSlot : timeSlots) {
            Date date = DateTimeUtils.getStartOfDay(timeSlot.getDateTime());

            if (!date.equals(currentDate)) {
                items.add(new DateHeaderItem(date));
                currentDate = date;
            }

            items.add(new TimeSlotItem(timeSlot));
        }

        return items;
    }

    private boolean isTimeSlotBooked(TimeSlot timeSlot) {
        // Check if the time slot is booked
        for (BookedTimeSlot bookedTimeSlot : bookedTimeSlots) {
            if (timeSlot.getDateTime().toString().equals(bookedTimeSlot.getDateTime())) {
                return true;
            }
        }
        return false;
    }

    public void setBookedTimeSlots(List<BookedTimeSlot> bookedTimeSlots) {
        this.bookedTimeSlots = bookedTimeSlots;
        notifyDataSetChanged();
    }

    public interface OnTimeSlotClickListener {
        void onTimeSlotClick(TimeSlot timeSlot);
    }

    // View holder classes for date header and time slot
    private static class DateHeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTextView;

        DateHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }

        void bindDateHeader(Date date) {
            dateTextView.setText(DateTimeUtils.formatDate(date));
        }
    }

    private static class TimeSlotViewHolder extends RecyclerView.ViewHolder {

        private TextView timeSlotTextView;

        TimeSlotViewHolder(@NonNull View itemView) {
            super(itemView);
            timeSlotTextView = itemView.findViewById(R.id.timeSlotTextView);
        }

        void bindTimeSlot(TimeSlot timeSlot, boolean isBooked) {
            timeSlotTextView.setText(DateTimeUtils.formatTime(timeSlot.getDateTime()));
            if (isBooked) {
                // Display booked time slots in a different color
                timeSlotTextView.setTextColor(Color.RED);
            } else {
                // Reset text color for available time slots
                timeSlotTextView.setTextColor(Color.BLACK);
            }
        }
    }

    private static class DateHeaderItem {
        private Date date;

        DateHeaderItem(Date date) {
            this.date = date;
        }

        Date getDate() {
            return date;
        }
    }

    private static class TimeSlotItem {
        private TimeSlot timeSlot;

        TimeSlotItem(TimeSlot timeSlot) {
            this.timeSlot = timeSlot;
        }

        TimeSlot getTimeSlot() {
            return timeSlot;
        }
    }
}