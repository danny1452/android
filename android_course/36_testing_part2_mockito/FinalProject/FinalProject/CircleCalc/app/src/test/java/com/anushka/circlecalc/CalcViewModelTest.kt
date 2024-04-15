package com.anushka.circlecalc


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CalcViewModelTest{

    private lateinit var calcViewModel: CalcViewModel
    // declare object
    private lateinit var calculations: Calculations

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()// for single thread execution


    @Before
    fun setUp() {
        calculations = Mockito.mock(Calculations::class.java)// mock creation
        Mockito.`when`(calculations.calculateArea(2.1)).thenReturn(13.8474) // mock usage setting
        Mockito.`when`(calculations.calculateCircumference(1.0)).thenReturn(6.28)
        calcViewModel = CalcViewModel(calculations)// use mock
    }

    @Test
    fun calculateArea_radiusSent_updateLiveData(){
       calcViewModel.calculateArea(2.1)// mock setting usage internally
       val result = calcViewModel.areaValue.value
       assertThat(result).isEqualTo("13.8474")
    }

    @Test
    fun calculateCircumference_radiusSent_updateLiveData(){
        calcViewModel.calculateCircumference(1.0)
        val result = calcViewModel.circumferenceValue.value
        assertThat(result).isEqualTo("6.28")
    }
}














