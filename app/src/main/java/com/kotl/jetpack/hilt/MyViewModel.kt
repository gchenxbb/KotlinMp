package com.kotl.jetpack.hilt

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

//第一种方式
@ActivityRetainedScoped
class MyViewModel1 @Inject constructor(val repository: Repository) : ViewModel() {
}

//第二种方式
@HiltViewModel
class MyViewModel2 @Inject constructor(val repository: Repository) : ViewModel() {
}