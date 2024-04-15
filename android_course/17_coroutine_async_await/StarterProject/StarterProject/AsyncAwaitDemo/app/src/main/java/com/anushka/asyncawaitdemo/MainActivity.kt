package com.anushka.asyncawaitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch {
            println("##### Getting stocks")
            val puneStock = async(Dispatchers.IO) {
                getPuneStock()
            }
            val nagarStock = async(Dispatchers.IO) {
                getNagarStock()
            }
            val total = puneStock.await() + nagarStock.await()
            println("##### Total stock : $total")
        }
        println("##### control is here now")
    }
}

private suspend fun getPuneStock() : Int {
    delay(10000)
    return 1500
}

private suspend fun getNagarStock() : Int{
    delay(8000)
    return 1200
}