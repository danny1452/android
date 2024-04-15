package com.example.appointment_poc;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    private TextView selectedTimeSlotTextView;
    private EditText clientNameEditText;
    private EditText mobileNumberEditText;
    private Button confirmBookingButton;

    private String selectedTimeSlot;
    private TimeSlotDatabase timeSlotDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        timeSlotDatabase = MyApplication.getTimeSlotDatabase();

        // Initialize views
        selectedTimeSlotTextView = findViewById(R.id.selectedTimeSlotTextView);
        clientNameEditText = findViewById(R.id.clientNameEditText);
        mobileNumberEditText = findViewById(R.id.mobileNumberEditText);
        confirmBookingButton = findViewById(R.id.confirmBookingButton);

        // Get selected time slot from Intent
        Intent intent = getIntent();
        if (intent.hasExtra("selectedTimeSlot")) {
            selectedTimeSlot = intent.getStringExtra("selectedTimeSlot");
            updateSelectedTimeSlotText();
        }

        // Handle confirm booking button click
        confirmBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the booking confirmation logic
                try {
                    confirmBooking();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void updateSelectedTimeSlotText() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm a", Locale.getDefault());
        selectedTimeSlotTextView.setText("Selected Time Slot: " + selectedTimeSlot);
    }

    private void confirmBooking() throws InterruptedException {
        // Retrieve client name and mobile number
        String clientName = clientNameEditText.getText().toString().trim();
        String mobileNumber = mobileNumberEditText.getText().toString().trim();

        // Perform validation if needed

        // You can implement the booking confirmation logic here
        // For simplicity, let's just show a toast message
        String confirmationMessage = "Booking confirmed for " + clientName +
                " on " + selectedTimeSlotTextView.getText().toString();
        // Display a toast or handle the confirmation as per your application logic
        Toast.makeText(this, confirmationMessage, Toast.LENGTH_SHORT).show();
        new Thread(() -> {
            timeSlotDatabase.bookedTimeSlotDao().insert(new BookedTimeSlot(selectedTimeSlot.toString()));
        }).start();

        // send sms
        sendSMS(mobileNumber, "Hello, "+ clientName + " your appointment with Dnyanesh is scheduled for " + selectedTimeSlotTextView.getText().toString());

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        //Thread.sleep(1500);
        finish();
    }

    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);

            // Display a toast indicating the SMS was sent successfully
            Toast.makeText(getApplicationContext(), "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Handle exceptions related to sending SMS, such as permission issues or other errors
            e.printStackTrace();

            // Display a toast indicating the SMS could not be sent
            Toast.makeText(getApplicationContext(), "SMS failed to send", Toast.LENGTH_SHORT).show();
        }
    }
}
