package com.kotl.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class BaseObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        Log.e(javaClass.simpleName, "BaseObserver-onLifecycleChanged:" + event.name)
    }
}