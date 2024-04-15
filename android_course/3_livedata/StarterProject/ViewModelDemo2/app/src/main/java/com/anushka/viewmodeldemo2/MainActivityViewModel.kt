package com.anushka.viewmodeldemo2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal : Int) : ViewModel() {
    private var totalMutableLiveData = MutableLiveData<Int>()
    val totalLiveData: LiveData<Int>
    get() = totalMutableLiveData

    init {
        totalMutableLiveData.value = startingTotal
    }

    fun setTotal(input:Int){
        totalMutableLiveData.value = totalMutableLiveData.value?.plus(input)
    }
}