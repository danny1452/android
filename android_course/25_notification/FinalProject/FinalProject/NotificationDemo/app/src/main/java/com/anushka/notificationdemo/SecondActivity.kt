package com.anushka.notificationdemo

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        receiveInput()
    }

    private fun receiveInput() {
        // use same key used while creating remote input
        val KEY_REPLY = "key_reply"
        val result_text_view = findViewById<TextView>(R.id.result_text_view)
        // get intent used to launch this activity
        val intent = this.intent
        // get remote bundle from intent using remote input method
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput != null) {
            // get specific string with unique key replay
            val inputString = remoteInput.getCharSequence(KEY_REPLY).toString()
            // display that string
            result_text_view.text = inputString

            // now update that same notification
            // identify notification using channel id and notification id
            val channelID = "com.anushka.notificationdemo.channel1"
            val notificationId = 45

            // create new notification
            val repliedNotification = NotificationCompat.Builder(this,channelID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentText("Your reply received")
                .build()
            // get notification manager
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // notify using id
            notificationManager.notify(notificationId,repliedNotification)
            // you can use cancel method also
        }
    }
}