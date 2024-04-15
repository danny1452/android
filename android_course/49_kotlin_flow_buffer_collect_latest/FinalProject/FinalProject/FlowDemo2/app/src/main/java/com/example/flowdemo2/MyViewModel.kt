package com.example.flowdemo2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    val myFlow = flow<Int> {
        for (i in 1..100){
            emit(i)
            delay(1000L)
        }
    }

    // execute when view model created
    init {
        backPressureDemo()
    }


    private fun backPressureDemo(){
        // producer
        // flow declaration
        val myFlow1 = flow<Int> {
            for (i in 1..10){
                Log.i("MYTAG","Produced $i")
                // value data stream
                emit(i)
                delay(1000L)
            }
        }

        // consumer
        // coroutine view model scope
        viewModelScope.launch {
            myFlow1.buffer().collect {// myFlow1.collectLatest - lasted value collectd only
                // myFlow1.buffer().collect - maintains buffer
               delay(2000L)
               Log.i("MYTAG","Consumed $it")
            }
        }



    }
}