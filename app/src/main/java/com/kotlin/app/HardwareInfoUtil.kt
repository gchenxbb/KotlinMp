package com.kotlin.app

import android.content.Context
import android.os.Build

/**
 * 设备信息
 */
class HardwareInfoUtil private constructor(private val context: Context) {

    val boradName: String = Build.BOARD//主板名字
    val phoneInfo: String = Build.BOOTLOADER //主板版本 号码
    val brand: String = Build.BRAND//出厂 品牌
    val sdkVersion: Int = Build.VERSION.SDK_INT//手机sdk
    val systemVersion: String = Build.VERSION.RELEASE//手机系统版本
    val display: String = Build.DISPLAY//显示设备版本
    val phoneVersion: String = Build.PRODUCT//手机型号名称
    val manufacturer: String = Build.MANUFACTURER//制造商
    val deviceName: String = Build.DEVICE//驱动名
    val fingerprint: String = Build.FINGERPRINT//设备唯一标识符
    val host: String = Build.HOST//主机地址
    val codeName: String = Build.ID//系统当前开发版本号


    companion object {
        var hardwareInfoUtil: HardwareInfoUtil? = null

        fun initHardwareInfo(context: Context): HardwareInfoUtil {
            if (hardwareInfoUtil == null) {
                hardwareInfoUtil = HardwareInfoUtil(context)
            }
            return hardwareInfoUtil!!
        }

    }


}