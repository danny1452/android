package com.anushka.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class MainActivity : AppCompatActivity() {
    // unique channel id to identify unique channel of notification
    private val channelID = "com.anushka.notificationdemo.channel1"
    private var notificationManager: NotificationManager? = null
    // define unique key to identify user input for notification
    private val KEY_REPLY = "key_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        // get notification manager from system
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // create channel
        createNotificationChannel(channelID,"DemoChannel","this is a demo")
        // set button onclick listener - here we are creating notification if and only if button is clicked
        button.setOnClickListener {
            displayNotification()
        }
    }


    private fun displayNotification(){
        // create unique notification id to uniquely identify notification
        val notificationId = 45

        // create intent for second activity
        val tapResultIntent = Intent(this, SecondActivity::class.java)
        // add this intent in pending intent so that notification manager can execute this code with
        // permission of this app
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            tapResultIntent,
            PendingIntent.FLAG_MUTABLE,
        )

        // create reply input using remote input builder also set label for it
        // pass unique key so that we will get this in second activity
        val remoteInput: RemoteInput = RemoteInput.Builder(KEY_REPLY).run{
            setLabel("Insert you name here")
            build()
        }

        // create reply action using notification compat action
        // add pending intent to it so it know what to do when reply submitted
        // also add remote input to take input
        val replyAction : NotificationCompat.Action = NotificationCompat.Action.Builder(
            0,
            "REPLY",
            pendingIntent
        ).addRemoteInput(remoteInput)
            .build()

        //action button 1
        val intent2 = Intent(this, DetailsActivity::class.java)
        val pendingIntent2: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent2,
            PendingIntent.FLAG_MUTABLE
        )

        val action2 : NotificationCompat.Action =
            NotificationCompat.Action.Builder(0,"Details",pendingIntent2).build()
        // action button 2
        val intent3 = Intent(this, SettingsActivity::class.java)
        val pendingIntent3: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent3,
            PendingIntent.FLAG_IMMUTABLE
        )
        val action3 : NotificationCompat.Action =
            NotificationCompat.Action.Builder(0,"Settings",pendingIntent3).build()


        // create notification using notification compat builder
        // add title, text, icon, priority, actions we created above
        val notification = NotificationCompat.Builder(this@MainActivity,channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(action2)
            .addAction(action3)
            .addAction(replyAction)
            .build()
        // display notification using notification manager
        notificationManager?.notify(notificationId,notification)

    }

    private fun createNotificationChannel(id : String, name:String, channelDescription:String){
        // check sdk version
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            // create notification channel with parameter id, name, priority
            val channel = NotificationChannel(id,name,importance).apply {
                description = channelDescription
            }
            // inform system about channel via notification manger
            // register channel with system
            notificationManager?.createNotificationChannel(channel)
        }
    }
}