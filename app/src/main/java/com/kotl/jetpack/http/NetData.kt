package com.kotl.jetpack.http

import android.util.Log
import com.kotl.jetpack.data.Recipes
import com.kotl.jetpack.data.RecipesItem
import com.kotl.jetpack.error.NETWORK_ERROR
import com.kotl.jetpack.error.NO_INTERNET_CONNECTION
import com.kotl.jetpack.data.Resource
import com.kotl.jetpack.networkconnect.NetworkConnectivity
import com.kotl.jetpack.service.ApiService
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by chenguang
 */
class NetData @Inject  constructor(private val apiService: ApiService, private val networkConnectivity: NetworkConnectivity) {
    suspend fun requestRecipes(): Resource<Recipes> {
        Log.d("NetData",  apiService.toString())
//        val recipesService = serviceGenerator.createService(ApiService::class.java)
        return when (val response = processCall(apiService::fetchRecipes)) {
            is List<*> -> {
                Resource.Success(data = Recipes(response as ArrayList<RecipesItem>))
            }
            else -> {
                Resource.DataError(exceptionCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
