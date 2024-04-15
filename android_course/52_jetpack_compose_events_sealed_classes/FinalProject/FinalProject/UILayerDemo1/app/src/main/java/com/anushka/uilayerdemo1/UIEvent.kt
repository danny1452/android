package com.anushka.uilayerdemo1

// event 2 - ui event
// same event can be triggered with different value as per requirement
// modularization of event
sealed class UIEvent{
    data class ShowMessage(val message:String):UIEvent()
}
