package com.anushka.didemo

import dagger.Component
import javax.inject.Singleton

@Singleton// to have only one copy
@Component(modules = [MemoryCardModule::class,NCBatteryModule::class])
interface SmartPhoneComponent {

   fun inject(mainActivity: MainActivity)
}

