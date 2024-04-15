package com.anushka.didemo

import android.app.Application

// own application class - this gets execute before first activity creation
class SmartPhoneApplication : Application() {
    lateinit var smartPhoneComponent: SmartPhoneComponent
    override fun onCreate() {
        // use this object in activities
        smartPhoneComponent = initDagger()
        super.onCreate()
    }

    private fun initDagger(): SmartPhoneComponent =
        DaggerSmartPhoneComponent.builder()// need to use builde for constructor parameter
            .memoryCardModule(MemoryCardModule(1000))
            .build()
}