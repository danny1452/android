package com.example.two_way_data_binding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    var name = MutableLiveData<String>();

    init {
        name.value = "Danny"
    }
}