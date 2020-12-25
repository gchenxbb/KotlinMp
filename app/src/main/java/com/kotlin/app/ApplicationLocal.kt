package com.kotlin.app

import android.graphics.drawable.Drawable

data class ApplicationLocal(
        val name: String,
        val packageName: String,
        val icon: Drawable,
        val versionName: String,
        val targetSdkVersion: Int,
        val minSdkVersion: Int = 0,
        val longVersionCode: Long,
        val firstInstallTime: Long,
        val lastUpdateTime: Long,
        val sourceDir: String,
        val isSystemApp: Boolean,
        val dataDir: String?,
        val sigMd5: String
) {

}