package com.anushka.viewmodelscopedemo

import kotlinx.coroutines.delay

class StudentRepository {

    suspend fun getStudentData() : List<Student>{
        delay(3000)
        return listOf(Student(1, "Mahesh"), Student(2, "Ganesh"))
    }
}