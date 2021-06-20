package com.kotl.jetpack.viewmodel.changefrag

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _data: MutableLiveData<Int> = MutableLiveData()
    val selected: MutableLiveData<Int> get() = _data

    fun update(value: Int) {
        selected.value = value
    }
}