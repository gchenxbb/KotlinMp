package com.kotl.jetpack.databinding

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class BindViewModelFactory(
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null)
    : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return BindViewModel() as T
    }
}