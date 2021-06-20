package com.kotl.jetpack.databinding

import androidx.databinding.ObservableField

data class BindBean(
        var phone: ObservableField<String> = ObservableField(""),
        var pwd: ObservableField<String> = ObservableField("")
) {
    override fun toString(): String {
        return "BindBean(phone=$phone, pwd=$pwd)"
    }
}
