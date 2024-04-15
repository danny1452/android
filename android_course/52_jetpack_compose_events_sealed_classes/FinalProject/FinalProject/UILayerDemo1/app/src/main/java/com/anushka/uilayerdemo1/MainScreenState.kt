package com.anushka.uilayerdemo1

// data class for keeping state of main screen -
data class MainScreenState(
    var isCountButtonVisible : Boolean = false,
    var displayingResult : String = "",
    var inputValue : String =""
)
