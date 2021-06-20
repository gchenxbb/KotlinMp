package com.kotl.jetpack.hilt

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by chenguang
 */
interface ExpApiService {
    @GET("app.do")
    suspend fun requestApi(): Response<Object>
}
