package com.kotl.jetpack.hilt

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.kotl.jetpack.BindDemoRetrofit
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

class MyContantProvider : ContentProvider() {

    //自定义一个自己的入口点
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MyEntryPoint {
        @BindDemoRetrofit
        fun getRetrofit(): Retrofit
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        context?.let {
            val appContext = it.applicationContext
            val entryPoint = EntryPointAccessors.fromApplication(appContext, MyEntryPoint::class.java)
            val retrofit = entryPoint.getRetrofit()
        }
        return 1
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        TODO("Not yet implemented")
    }

    override fun onCreate(): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }
}