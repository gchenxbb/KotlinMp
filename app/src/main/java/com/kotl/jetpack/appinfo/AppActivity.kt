package com.kotl.jetpack.appinfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotl.jetpack.R
import com.kotl.jetpack.data.ApplicationLocal
import com.kotl.jetpack.util.AppInfoUtil
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppActivity : AppCompatActivity() {
    val tag: String = "AppActivity"

    private lateinit var appList: MutableList<ApplicationLocal>
    private lateinit var appRecyclerAdapter: AppInfoRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applist)
        loadApp()
    }

    @SuppressLint("CheckResult")
    fun loadApp() {
        Observable.create(ObservableOnSubscribe<MutableList<ApplicationLocal>> {
            val appList = AppInfoUtil.getAllApplication(this@AppActivity)
            it.onNext(appList)
            it.onComplete()
        }).subscribeOn(Schedulers.io()).doOnSubscribe {
            startLoading()
        }.doFinally {
            Log.d(tag, "doFinally:${Thread.currentThread().name}")
        }.observeOn(AndroidSchedulers.mainThread()).subscribe {
            Log.d(tag, "subscribe:${Thread.currentThread().name}")
            appList = it
            initView()
        }
    }


    fun startLoading() {
        Log.d(tag, "startLoading:${Thread.currentThread().name}")
    }

    private fun initView() {
        recyclerView = findViewById(R.id.rv_appList)
        appRecyclerAdapter = AppInfoRecyclerAdapter(appList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = appRecyclerAdapter
        appRecyclerAdapter.setOnItemClickListener(object : AppInfoRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                TODO("Not yet implemented")
            }
        })

    }


}