package com.kotl.jetpack.hilt

import javax.inject.Qualifier

interface Engine {
    fun start()
    fun shutdown()
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindGasEngine

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindElectricEngine
