package com.kotl.jetpack.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.kotl.jetpack.R

class LifecycleActivity : AppCompatActivity() {

    private lateinit var myObserver: BaseObserver

    private lateinit var myFullLifecycleObserver: MyFullLifecycleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        myObserver = BaseObserver()
        lifecycle.addObserver(myObserver)

        myFullLifecycleObserver = MyFullLifecycleObserver()
        lifecycle.addObserver(myFullLifecycleObserver)

        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            }
        })
    }
}