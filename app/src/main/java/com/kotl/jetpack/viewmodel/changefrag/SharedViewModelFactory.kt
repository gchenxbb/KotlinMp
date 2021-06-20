package com.kotl.jetpack.viewmodel.changefrag

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class SharedViewModelFactory(
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null)
    : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return SharedViewModel() as T
    }
}