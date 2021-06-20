package com.kotl.jetpack

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ProcessLifecycleOwner
import com.kotl.jetpack.lifecycle.AppObserver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KotApplication : Application() {

    companion object {
        lateinit var appContext: Application
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.d("AppInitializer", "attachBaseContext")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("AppInitializer", "onCreate")
        appContext = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppObserver())

    }
}