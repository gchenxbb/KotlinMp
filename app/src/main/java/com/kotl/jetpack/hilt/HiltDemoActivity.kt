package com.kotl.jetpack.hilt

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kotl.jetpack.BindDemoOkhttpClient
import com.kotl.jetpack.BindDemoRetrofit
import com.kotl.jetpack.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import okhttp3.*
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject

/**
 * Created by chenguang
 * Hilt的工作原理是从Application的onCreate方法开始，ContentProvider声明周期在之前就会执行，不适合Hilt入口

 * @AndroidEntryPoint,入口点，一共6个
 *
 * Application，Activity，Fragment，View，Service，BroadcastReceiver
 */
@AndroidEntryPoint
class HiltDemoActivity : AppCompatActivity() {
    //hilt注入，不可private
    @Inject
    lateinit var truck: Truck

    @BindDemoOkhttpClient
    @Inject
    lateinit var client: OkHttpClient

    @BindDemoRetrofit
    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var viewModel1: MyViewModel1

    //不再使用@Inject
    private val viewmodel2: MyViewModel2 by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hiltdemo)
        truck.deliver()

        request()

        GlobalScope.launch {
            requestApi()
        }
    }

    fun request() {
        val request = Request.Builder().get().url("https://www.baidu.com").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("HtilClient", "onFailure:$e")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("HtilClient", "onResponse:" + response.body?.toString())
            }
        })
    }

    suspend fun requestApi() {
        val service = retrofit.create(ExpApiService::class.java)

        flow {
            emit(service.requestApi())
        }.flowOn(Dispatchers.IO).collect {
            Log.d("HtilClient", "it:$it")
        }
    }
}