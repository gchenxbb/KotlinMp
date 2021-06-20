package com.kotl.jetpack.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotl.jetpack.error.ErrorManager
import com.kotl.jetpack.event.SingleEvent
import javax.inject.Inject

/**
 * Created by chenguang
 */
abstract class BaseViewModel : ViewModel() {
    @Inject
    lateinit var errorManager: ErrorManager

    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}
