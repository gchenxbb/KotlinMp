package com.kotlin.app

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadApp()
    }


    @SuppressLint("CheckResult")
    fun loadApp() {
        Observable.create(ObservableOnSubscribe<MutableList<ApplicationLocal>> {
            Log.d("threade", "ObservableEmitter:${Thread.currentThread().name}")
            AppInfoUtil.init(this@AppActivity)
            val mutableList = AppInfoUtil.getAllApplication(this@AppActivity)
            it.onNext(mutableList)
            it.onComplete()
        }).subscribeOn(Schedulers.io()).doOnSubscribe {
            Log.d("threade", "doOnSubscribe:${Thread.currentThread().name}")
        }.doFinally {
            Log.d("threade", "doFinally:${Thread.currentThread().name}")
        }.observeOn(AndroidSchedulers.mainThread()).subscribe {
            Log.d("threade", "subscribe:${Thread.currentThread().name}")
        }
    }
}