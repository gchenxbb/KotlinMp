package com.kotl.jetpack

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindProOkhttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindDemoOkhttpClient


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindProRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindDemoRetrofit