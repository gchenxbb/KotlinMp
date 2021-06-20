package com.kotl.jetpack.viewmodel

import androidx.lifecycle.*


class UserViewModel(
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val SAVED_STATE_KEY = "SAVED_STATE_KEY"
    private lateinit var users: MutableLiveData<List<User>>

    fun getUsers(): LiveData<List<User>> {
        if (!::users.isInitialized) {
            users = MutableLiveData()
            Thread(Runnable {
                Thread.sleep(600)
                users.postValue(listOf(User("1", "陈光1"), User("2", "陈光2"), User("3", "陈光3")))
            }).start()
        }
        return users
    }

    var loginStatus: MutableLiveData<Boolean> = MutableLiveData()

    var savedValue: LiveData<Int> = getSavedNumber()

    fun setValue(num: Int) {
        savedStateHandle.set(SAVED_STATE_KEY, num)
    }

    private fun getSavedNumber(): MutableLiveData<Int> {
        return savedStateHandle.getLiveData(SAVED_STATE_KEY, -1)
    }
}