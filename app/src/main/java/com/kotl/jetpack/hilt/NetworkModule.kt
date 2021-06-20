package com.kotl.jetpack.hilt

import com.kotl.jetpack.BindDemoOkhttpClient
import com.kotl.jetpack.BindDemoRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * @InstallIn
 *
 * ApplciationComponent ,@Singleton,全项目可用。
 * ActivityComponent ,@ActivityScoped
 * FragmentComponent ,@FragmentyScoped
 * ViewComponent,@ViewScoped
 * ViewWithFragmentComponent ,@ViewScoped
 * ServiceComponent ,@ServiceScoped
 * ViewModel ,ActivityRetainedComponent,@ActivityRetainedScope
 */
@Module
@InstallIn(SingletonComponent::class)//ActivityComponent  只能在activity中注入使用，或fragment，view。其他如service不可用
class NetworkModule {

    @BindDemoOkhttpClient
    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
    }

    @BindDemoRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(@BindDemoOkhttpClient okhttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.baidu.com")
                .client(okhttpClient)
                .build()
    }

}