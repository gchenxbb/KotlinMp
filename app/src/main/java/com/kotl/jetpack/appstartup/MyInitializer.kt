package com.kotl.jetpack.appstartup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

class MyInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Log.d("AppInitializer", "create.")
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        Log.d("AppInitializer", "dependencies.")
        //当前MyInitializer是否依赖其他Initialize。保证先初始化依赖的Initializer
        return listOf(OtherInitializer::class.java)
    }
}