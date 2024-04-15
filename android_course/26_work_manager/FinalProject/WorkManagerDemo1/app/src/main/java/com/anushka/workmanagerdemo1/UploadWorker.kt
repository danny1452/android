package com.anushka.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

//worker class extending worker  - it takes parameter like context , Worker Parameters
// need to override doWork
class UploadWorker(context: Context,params:WorkerParameters) : Worker(context,params) {

    companion object{
        // to uniquely identify worker ?
        const val KEY_WORKER = "key_worker"
    }

    // system will run this code
    override fun doWork(): Result {
        try {
            // get data, set in main activity using worker request
            val count = inputData.getInt(MainActivity.KEY_COUNT_VALUE,0)
            // execute background task
            for (i in 0 until count) {
                Log.i("MYTAG", "Uploading $i")
            }

            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())

            val outPutData = Data.Builder()
                .putString(KEY_WORKER,currentDate)
                .build()

            // put data to be accesed using live data in main activity
            return Result.success(outPutData)
        } catch (e:Exception){
            return Result.failure()
        }
    }
}