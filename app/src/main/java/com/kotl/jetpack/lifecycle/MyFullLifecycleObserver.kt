package com.kotl.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 *  FullLifecycleObserver不对外,用DefaultLifecycleObserver
 */
class MyFullLifecycleObserver : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.e(javaClass.simpleName, "MyFullLifecycleObserver-onCreate:")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.e(javaClass.simpleName, "MyFullLifecycleObserver-onResume:")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.e(javaClass.simpleName, "MyFullLifecycleObserver-onPause:")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.e(javaClass.simpleName, "MyFullLifecycleObserver-onStart:")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.e(javaClass.simpleName, "MyFullLifecycleObserver-onStop:")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.e(javaClass.simpleName, "MyFullLifecycleObserver-onDestroy:")
    }
}