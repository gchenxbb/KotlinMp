package com.kotlin.app

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build

object AppInfoUtil {

    fun init(context: Context) {
        val packageInfoList = context.packageManager.getInstalledPackages(PackageManager.GET_SIGNATURES)
        for (packageInfo in packageInfoList) {
            val applicationInfo = packageInfo.applicationInfo
            val application = ApplicationLocal(
                    packageName = packageInfo.packageName,
                    versionName = packageInfo.versionName ?: "",
                    targetSdkVersion = applicationInfo.targetSdkVersion,
                    minSdkVersion = if (Build.VERSION.SDK_INT > 23) applicationInfo.minSdkVersion else 0,
                    longVersionCode = packageInfo.versionCode.toLong(),
                    firstInstallTime = packageInfo.firstInstallTime,
                    lastUpdateTime = packageInfo.lastUpdateTime,
                    isSystemApp = isSystemApplication(packageInfo),
                    icon = applicationInfo.loadIcon(context.packageManager),
                    name = applicationInfo.loadLabel(context.packageManager).toString(),
                    sourceDir = applicationInfo.sourceDir,
                    dataDir = applicationInfo.dataDir,
                    sigMd5 = ""
//                    sigMd5 = packageInfo.signatures?.let {
//                        if (packageInfo.signatures.isNotEmpty())
//                            getSignValidString(packageInfo.signatures[0].toByteArray())
//                        else
//                            ""
//                    } ?: ""
            )
        }
    }

    /**
     * 判断是否是系统应用
     */
    private fun isSystemApplication(packageInfo: PackageInfo): Boolean {
        return packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }

    /**
     * 获取设备所有的应用
     */
    fun getAllApplication(context: Context): MutableList<ApplicationLocal> {
        return getApplicationInfo(
                context,
                ApplicationType.AllApplication
        )
    }

    /**
     * 获取设备的应用信息
     */
    private fun getApplicationInfo(
            context: Context,
            applicationType: ApplicationType
    ): MutableList<ApplicationLocal> {

        val applicationList = mutableListOf<ApplicationLocal>()

        return applicationList
    }

    private enum class ApplicationType {
        AllApplication, NonSystemApplication, SystemApplication
    }


}