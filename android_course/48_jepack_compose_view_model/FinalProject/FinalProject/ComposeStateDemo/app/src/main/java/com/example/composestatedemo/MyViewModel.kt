package com.example.composestatedemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    // use mutable state of instead of mutable live data
 var count by mutableStateOf(0)

 fun increaseCount(){
     count++
 }

}