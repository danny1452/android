package com.anushka.coroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var btnDownloadUserData : Button
    private lateinit var btnCount : Button
    private lateinit var tvCount : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDownloadUserData = findViewById(R.id.btnDownloadUserData)
        btnCount = findViewById(R.id.btnCount)
        tvCount = findViewById(R.id.tvCount)

        // simple count increment and displaying
        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }

        // long background running task
        btnDownloadUserData.setOnClickListener {
            // created coroutine scope here
            CoroutineScope(Dispatchers.IO).launch {// with launch so no return value
                // with io dispatcher so background thread
                downloadUserData()
            }
        }
    }

    private fun downloadUserData() {
        for (i in 1..200000) {
            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
        }
    }
}