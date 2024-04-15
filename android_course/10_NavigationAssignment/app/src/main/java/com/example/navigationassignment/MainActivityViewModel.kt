package com.example.navigationassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData<String>()
}