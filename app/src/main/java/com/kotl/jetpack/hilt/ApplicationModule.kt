package com.kotl.jetpack.hilt

import android.app.Application
import com.kotl.jetpack.KotApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    //参数是Application不需要注释，自动注入，KotApplication是全局唯一，因此可向下转换
    @Provides
    fun provideKotApplication(application: Application): KotApplication {
        return application as KotApplication
    }

}