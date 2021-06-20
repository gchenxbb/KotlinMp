package com.kotl.jetpack.room

import android.content.Context
import androidx.fragment.app.Fragment

object Injector {

    private fun getStdRepository(context: Context): StudentRepository {
        return StudentRepository.getInstance(StuDatabase.getInstance(context.applicationContext).studentDao())
    }

    fun provideStdListViewModelFactory(fragment: Fragment):StdListViewModelFactory{
        return StdListViewModelFactory(getStdRepository(fragment.requireContext()),fragment)
    }

}