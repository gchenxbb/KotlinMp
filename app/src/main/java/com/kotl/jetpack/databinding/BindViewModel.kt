package com.kotl.jetpack.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 */
class BindViewModel() : ViewModel() {
    var bindValue: MutableLiveData<BindBean> = MutableLiveData()
    var editValue: MutableLiveData<String> = MutableLiveData()
}
