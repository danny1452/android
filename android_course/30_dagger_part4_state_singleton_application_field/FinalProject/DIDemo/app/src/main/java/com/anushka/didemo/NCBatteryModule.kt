package com.anushka.didemo

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module// module for third part classes
abstract class NCBatteryModule {
    @Binds// for abtract methods
    abstract fun bindsNCBattery(nickelCadmiumBattery: NickelCadmiumBattery):Battery
}