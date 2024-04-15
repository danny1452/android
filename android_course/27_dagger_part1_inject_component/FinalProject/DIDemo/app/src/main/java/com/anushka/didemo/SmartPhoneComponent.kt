package com.anushka.didemo

import dagger.Component

// @component annotation for interface - DaggerSmartPhoneComponent class will be generated
// method return type is import here
@Component
interface SmartPhoneComponent {
   fun getSmartPhone() : SmartPhone
}