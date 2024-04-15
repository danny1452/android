package com.anushka.didemo

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule(val memorySize:Int) {// constructor parameter - module with state - need to use builder instead of create for component

    @Provides
    fun providesMemoryCard():MemoryCard{
        Log.i("MYTAG","Size of the memory is $memorySize")
        return MemoryCard()
    }
}