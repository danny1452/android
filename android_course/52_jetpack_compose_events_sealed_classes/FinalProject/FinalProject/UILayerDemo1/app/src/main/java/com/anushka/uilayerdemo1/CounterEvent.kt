package com.anushka.uilayerdemo1

// event 1 - for button and edit box events
// state maintained in view model
// observed in main activity
// triggered in event blocks
sealed class CounterEvent{
       data class ValueEntered(val value : String) : CounterEvent()
       object CountButtonClicked : CounterEvent()
       object ResetButtonClicked : CounterEvent()
}
