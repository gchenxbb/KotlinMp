package com.kotl.jetpack.di

import android.content.Context
import android.util.Log
import com.kotl.jetpack.BindProOkhttpClient
import com.kotl.jetpack.BindProRetrofit
import com.kotl.jetpack.BuildConfig
import com.kotl.jetpack.constants.BASE_URL
import com.kotl.jetpack.json.MyStandardJsonAdapters
import com.kotl.jetpack.login.MyKotlinJsonAdapterFactory
import com.kotl.jetpack.networkconnect.Network
import com.kotl.jetpack.networkconnect.NetworkConnectivity
import com.kotl.jetpack.service.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity {
        return Network(context)
    }

    @Provides
    fun provideApirService(@BindProRetrofit retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @BindProRetrofit
    @Singleton
    @Provides
    fun provideRetrofit(@BindProOkhttpClient okHttp: OkHttpClient): Retrofit {
        val mosh = Moshi.Builder()
                .add(MyKotlinJsonAdapterFactory())
                .add(MyStandardJsonAdapters.FACTORY)
                .build()
        return Retrofit.Builder()
                .baseUrl(BASE_URL) // 设置OkHttpclient
                .client(okHttp)
                .addConverterFactory(MoshiConverterFactory.create(mosh)).build()
    }

    @BindProOkhttpClient
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            // OkHttp日志拦截器
            builder.addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    val strLength: Int = message.length
                    var start = 0
                    var end = 2000
                    for (i in 0..99) {
                        //剩下的文本还是大于规定长度则继续重复截取并输出
                        if (strLength > end) {
                            Log.d("okhttp", message.substring(start, end))
                            start = end
                            end += 2000
                        } else {
                            Log.d("okhttp", message.substring(start, strLength))
                            break
                        }
                    }
                }

            }).setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        var headerInterceptor = Interceptor { chain ->
            val original = chain.request()
            val contentType = "Content-Type"
            val contentTypeValue = "application/json"
            val request = original.newBuilder()
                    .header(contentType, contentTypeValue)
                    .method(original.method, original.body)
                    .build()

            chain.proceed(request)
        }
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.addInterceptor(headerInterceptor)
        return builder.build()
    }

}
