package com.kotlin.app

import android.app.Application

class KApplication : Application() {


    companion object {
        lateinit var appContext: Application
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this

    }
}