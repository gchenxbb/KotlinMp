package com.kotl.jetpack.appstartup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

class OtherInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Log.d("AppInitializer", "create.")
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        Log.d("AppInitializer", "dependencies.")
        return emptyList()
    }
}