package com.kotlin.app

import android.content.Context

object AContextHolder {

    val context: Context by lazy {
        KApplication.appContext
    }
}