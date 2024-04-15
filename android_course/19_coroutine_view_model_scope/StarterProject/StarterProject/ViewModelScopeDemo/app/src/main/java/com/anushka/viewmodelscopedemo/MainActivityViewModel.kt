package com.anushka.viewmodelscopedemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivityViewModel : ViewModel() {
    private val repository = StudentRepository()
    val resultLiveData = MutableLiveData<List<Student>?>()

     fun getUserData(){
         var result: List<Student>?
         viewModelScope.launch() {
             withContext(Dispatchers.IO) {
                 result = repository.getStudentData()
             }
             resultLiveData.value = result
             // this statement should be in scope only
         }
     }
}