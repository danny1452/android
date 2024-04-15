package com.anushka.didemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    // field injection of smart phone
@Inject
lateinit var smartPhone: SmartPhone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get application as our application -typecast - gt component - inject
        (application as SmartPhoneApplication).smartPhoneComponent
            .inject(this)
        smartPhone.makeACallWithRecording()

    }
}
