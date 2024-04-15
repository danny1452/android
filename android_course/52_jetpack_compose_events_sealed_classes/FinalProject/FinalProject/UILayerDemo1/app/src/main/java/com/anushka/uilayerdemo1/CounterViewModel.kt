package com.anushka.uilayerdemo1

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {

    // mutable state of - main screen state
    // event 1 state is screen state
    private val _screenState = mutableStateOf(MainScreenState(
        inputValue = "",
        displayingResult = "Total is 0.0"
    )
    )
    // encapsulation
    val screenState : State<MainScreenState> = _screenState

    // mutable state for event 2 ui event
    private val _uiEventFlow = MutableSharedFlow<UIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private var total = 0.0

    // helper functions to be called in on event
    private fun addToTotal(){
        // state updated
        total += _screenState.value.inputValue.toDouble()
        _screenState.value = _screenState.value.copy(
            displayingResult = "Total is $total",
            isCountButtonVisible = false
        )
    }

    private fun resetTotal(){
        // state updated
        total = 0.0
        _screenState.value = _screenState.value.copy(
            displayingResult = "Total is $total",
            inputValue = "",
            isCountButtonVisible = false
        )
    }

    fun onEvent(event: CounterEvent){
        when(event){
            is CounterEvent.ValueEntered -> {
                // state updated which observed in activity
               _screenState.value = _screenState.value.copy(
                  inputValue = event.value,
                  isCountButtonVisible = true
               )
            }
            is CounterEvent.CountButtonClicked -> {
                // business logic
                addToTotal()
                viewModelScope.launch {
                  _uiEventFlow.emit(
                    UIEvent.ShowMessage(
                        message = "Value added successfully"
                    )
                  )
                }
            }
            is CounterEvent.ResetButtonClicked -> {
                // business logic
                resetTotal()
                viewModelScope.launch {
                    _uiEventFlow.emit(// emit string value from here - to be listened in main activity
                        UIEvent.ShowMessage(
                            message = "Value reset successfully"
                        )
                    )
                }
            }

        }
    }




}