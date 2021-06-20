package com.kotl.jetpack.databindingsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 */
class LoginViewModel() : ViewModel() {
    var loginPhone: MutableLiveData<String> = MutableLiveData()
    var loginPwd: MutableLiveData<String> = MutableLiveData()
}
