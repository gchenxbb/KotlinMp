package com.kotl.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/***
 * Application
 */
class AppObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d(javaClass.name, "Application onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.d(javaClass.name, "Application onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d(javaClass.name, "Application onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.d(javaClass.name, "Application onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d(javaClass.name, "Application onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory() {
        Log.d(javaClass.name, "Application onDestory")
    }
}