package com.kotl.jetpack.hilt

import com.kotl.jetpack.KotApplication
import javax.inject.Inject

//Truck 的构造方法依赖的所有对象都支持依赖注入，Truck才可以被依赖注入
class Truck @Inject constructor(val driver: Driver, val kotapplication: KotApplication) {

    @BindGasEngine
    @Inject
    lateinit var gasEngine: Engine

    @BindElectricEngine
    @Inject
    lateinit var electricEngine: Engine

    fun deliver() {
        gasEngine.start()
        electricEngine.start()
        println("Trunk is delivering cargo,Driven by$driver")
        gasEngine.shutdown()
        electricEngine.shutdown()
    }
}