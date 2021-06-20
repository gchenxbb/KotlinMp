package com.kotl.jetpack.databindingsample

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class LoginViewModelFactory(
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null)
    : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return LoginViewModel() as T
    }
}