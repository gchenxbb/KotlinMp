package com.kotl.jetpack.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.kotl.jetpack.data.ApplicationLocal
import java.security.MessageDigest

object AppInfoUtil {

    val appMaps = mutableMapOf<String, ApplicationLocal>()


    fun init(context: Context) {
        appMaps.clear()
        val packageInfoList = context.packageManager.getInstalledPackages(PackageManager.GET_SIGNATURES)
        for (packageInfo in packageInfoList) {
            val applicationInfo = packageInfo.applicationInfo

            val sigMd5 = packageInfo.signatures?.let {
                getSignValidString(it[0].toByteArray())
            } ?: ""

            val appInfo = ApplicationLocal(
                    applicationInfo.loadLabel(context.packageManager).toString(),
                    packageInfo.packageName,
                    applicationInfo.loadIcon(context.packageManager),
                    packageInfo.versionName,
                    applicationInfo.targetSdkVersion,
                    if (Build.VERSION.SDK_INT > 23) applicationInfo.minSdkVersion else 0,
                    packageInfo.versionCode.toLong(),
                    packageInfo.firstInstallTime,
                    packageInfo.lastUpdateTime,
                    applicationInfo.sourceDir,
                    isSystemApplication(packageInfo),
                    applicationInfo.dataDir,
                    sigMd5
            )
            appMaps[appInfo.packageName] = appInfo
        }
    }

    private fun getSignValidString(signatures: ByteArray): String {
        val messageDigest = MessageDigest.getInstance("MD5")
        messageDigest.update(signatures)
        return toHexString(messageDigest.digest())
    }

    private fun toHexString(keyData: ByteArray): String {
        val strBuilder = StringBuilder(keyData.size * 2)
        for (keyDatum in keyData) {
            var hexStr = Integer.toString(keyDatum.toInt() and 255, 16)
            if (hexStr.length == 1) {
                hexStr = "0$hexStr"
            }
            strBuilder.append(hexStr)
        }
        return strBuilder.toString()
    }


    /**
     * 获取设备所有的应用
     */
    fun getAllApplication(context: Context): MutableList<ApplicationLocal> {
        return getApplicationInfo(
                context,
                ApplicationType.All
        )
    }

    /**
     * 获取设备的应用信息
     */
    private fun getApplicationInfo(
            context: Context,
            applicationType: ApplicationType
    ): MutableList<ApplicationLocal> {
        if (appMaps.isEmpty()) {
            init(context)
        }
        val applicationList = mutableListOf<ApplicationLocal>()

        when (applicationType) {
            ApplicationType.All -> {
                applicationList.addAll(appMaps.values)
            }
            ApplicationType.NonSystem -> {
                applicationList.addAll(appMaps.filter { entry -> entry.value.isSystemApp }.values)
            }
            ApplicationType.System -> {
                applicationList.addAll(appMaps.filter { entry -> !entry.value.isSystemApp }.values)
            }
        }
        return applicationList
    }

    private enum class ApplicationType {
        All, NonSystem, System
    }


    /**
     * 判断是否是系统应用
     */
    private fun isSystemApplication(packageInfo: PackageInfo): Boolean {
        return packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }

}